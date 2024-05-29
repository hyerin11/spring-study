import { BASE_URL } from './reply.js';

function getRelativeTime(createAt) {
  // 현재 시간 구하기
  const now = new Date();
  // 등록 시간 날짜타입으로 변환
  const past = new Date(createAt);

  // 사이 시간 구하기 (밀리초)
  const diff = now - past;
  // console.log(diff);

  const seconds = Math.floor(diff / 1000);
  const minutes = Math.floor(diff / 1000 / 60);
  const hours = Math.floor(diff / 1000 / 60 / 60);
  const days = Math.floor(diff / 1000 / 60 / 60 / 24);
  const weeks = Math.floor(diff / 1000 / 60 / 60 / 24 / 7);
  const years = Math.floor(diff / 1000 / 60 / 60 / 24 / 365);

  if (seconds < 60) {
    return '방금 전';
  } else if (minutes < 60) {
    return `${minutes}분 전`;
  } else if (hours < 24) {
    return `${hours}시간 전`;
  } else if (days < 7) {
    return `${days}일 전`;
  } else if (weeks < 52) {
    return `${weeks}주 전`;
  } else {
    return `${years}년 전`;
  }
}


function renderPage({ begin, end, pageInfo, prev, next }) {
    let tag = '';
  
    // prev 만들기
    if (prev) tag += `<li class='page-item'><a class='page-link page-active' href='${begin - 1}'> Prev </a></li>`;
  
    // 페이지 번호 태그 만들기
    for (let i = begin; i <= end; i++) {
  
      let active = '';
      if (pageInfo.pageNo === i) active = 'p-active';
  
      tag += `
        <li class='page-item ${active}'>
          <a class='page-link page-custom' href='${i}'>${i}</a>
        </li>`;
    }
  
    // next 만들기
    if (next) tag += `<li class='page-item'><a class='page-link page-active' href='${end + 1}'> Next </a></li>`;
  
    // 페이지 태그 ul에 붙이기
    const $pageUl = document.querySelector('.pagination');
    $pageUl.innerHTML = tag;
  }

export function renderReplies({ pageInfo, replies }) {
  // 댓글 수 렌더링
  document.getElementById('replyCnt').textContent = pageInfo.totalCount;

  // 댓글 목록 렌더링
  let tag = '';
  if (replies && replies.length > 0) {
    replies.forEach(({ reply_no: rno, writer, text, createAt }) => {
      tag += `
        <div id='replyContent' class='card-body' data-reply-id='${rno}'>
            <div class='row user-block'>
                <span class='col-md-3'>
                    <b>${writer}</b>
                </span>
                <span class='offset-md-6 col-md-3 text-right'><b>${getRelativeTime(
                  createAt
                )}</b></span>
            </div><br>
            <div class='row'>
                <div class='col-md-9'>${text}</div>
                <div class='col-md-3 text-right'>
                    <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;
                    <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>
                </div>
            </div>
        </div>
        `;
    });
  } else {
    tag = `<div id='replyContent' class='card-body'>댓글이 아직 없습니다!</div>`;
  }

  document.getElementById('replyData').innerHTML = tag;

  // 페이지 태그 렌더링
  renderPage(pageInfo);

}

//서버에서 댓글 목록 가져오는 비동기 요청 함수 
export async function fetchReplies(pageNo = 1) {
  const bno = document.getElementById('wrap').dataset.bno; // 게시물 글번호

  const res = await fetch(`${BASE_URL}/${bno}/page/${pageNo}`);
  const replyResponse = await res.json();
  // { pageInfo: {}, replies: [] }

  // 댓글 목록 렌더링
  renderReplies(replyResponse);
}

//페이지 클릭 이벤트 생성 함수 
export function replyPageClickEvent(){
  document.querySelector('.pagination').addEventListener('click', e =>{
    e.preventDefault();
    fetchReplies(e.target.getAttribute('href'));
    //console.log(e.target.getAttribute('href'));
  });
}


//========================= 무한 스크롤 전용 함수 ============================ //

//3.
let currentPage = 1; //현재 무한스크롤 시 진행되고 있는 페이지 번호
let isFetching = false; //성질 급한 사람이 데이터 불러오기 전 아래로 확 내렸을 때, 로직순서가 꼬일 수 있음.
//데이터를 불러오는 중에는 더 가져오지 않게 제어하기 위한 논리변수

//22. 전역변수 지정해줌
let totalReplies = 0; //총 댓글 수
let loadedReplies = 0; //로딩된 댓글 수 



//20.
function appendReplies({ replies }) {
  

  // 댓글 목록 렌더링
  let tag = '';
  if (replies && replies.length > 0) {
    replies.forEach(({ reply_no: rno, writer, text, createAt }) => {
      tag += `
        <div id='replyContent' class='card-body' data-reply-id='${rno}'>
            <div class='row user-block'>
                <span class='col-md-3'>
                    <b>${writer}</b>
                </span>
                <span class='offset-md-6 col-md-3 text-right'><b>${getRelativeTime(
                  createAt
                )}</b></span>
            </div><br>
            <div class='row'>
                <div class='col-md-9'>${text}</div>
                <div class='col-md-3 text-right'>
                    <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;
                    <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>
                </div>
            </div>
        </div>
        `;
    });
  } else {
    tag = `<div id='replyContent' class='card-body'>댓글이 아직 없습니다! ㅠㅠ</div>`;
  }

  document.getElementById('replyData').innerHTML += tag;

  //22. 위에서 화면 그리고 이제 다시 로드된 댓글 수 업데이트
  loadedReplies += replies.length;

}




//2. 서버에서 댓글 데이터를 페칭
export async function fetchInfScrollReplies(pageNo = 1){

//6. 지금 패칭중(서버에서 데이터를 가져오는 중이면 return)
if(isFetching) return;
isFetching = true;

  const bno = document.getElementById('wrap').dataset.bno; // 게시물 글번호

  const res = await fetch(`${BASE_URL}/${bno}/page/${pageNo}`);
  const replyResponse = await res.json();

  // 21.
  if(pageNo===1){

    //23. 총 댓글 수 전역변수 값 세팅
    totalReplies = replyResponse.pageInfo.totalCount
    loadedReplies = 0; //비동기라 댓글 입력/삭제 시 다시 1페이지 로딩. 그때 전역변수 값을 0 으로 초기화해줘야 함.


    //댓글 수 렌더링
    document.getElementById('replyCnt').textContent = totalReplies;
    //초기 댓글 리셋
    document.getElementById('replyData').innerHTML = ''; //깨끗하게 비우고 시작
  }

  // 댓글 목록 렌더링
  appendReplies(replyResponse); //21. 
  //console.log(replyResponse);
  currentPage = pageNo; //5. 늘어난 페이지로 현재 페이지 올려주기.

  isFetching = false;//7. 다 가지고 왔을 때


  //24. 댓글을 전부 가져올 시 스크롤 이벤트 제거하기
  if(loadedReplies >= totalReplies){
    window.removeEventListener('scroll', scrollHandler);
  }
  
}

function scrollHandler(e){

  // 1. 스크롤이 최하단부로 내려갔을 때 만 이벤트 발생시켜야 한다
  // 현재 창에 보이는 세로 길이 + 스크롤 내릴 길이가 >= 브라우저 전체 세로길이보다 커졌을 때 = 가장 최하단부이다.
  //                                                                 +500 미리 불러오고(나중에) -500하면 내리기 전 사전에 가져옴
  if(window.innerHeight + window.scrollY >= document.body.offsetHeight + 100){
    //console.log(e);
    //4. 서버에서 데이터를 비동기로 불러와야 함
    fetchInfScrollReplies(currentPage + 1); 
  }

  //console.log(e);
}


// 무한 스크롤 이벤트 생성 함수
export function setupInfinitScroll() {
  window.addEventListener('scroll', scrollHandler);
}




