
// ====== 전역 변수 ========
const BASE_URL = 'http://localhost:8383/api/v1/replies';
const bno = document.getElementById('wrap').dataset.bno; // 게시물 글번호
console.log('글번호: ', bno);

// ====== 함수 정의 ========
function renderReplies(replies) {

  // 댓글 수 렌더링
  document.getElementById('replyCnt').textContent = replies.length;

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
                <span class='offset-md-6 col-md-3 text-right'><b>${createAt}</b></span>
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

}

async function fetchReplies() {
  const res = await fetch(`${BASE_URL}/${bno}`);
  const replies = await res.json();

  // 댓글 목록 렌더링
  renderReplies(replies);
}

// ====== 실행 코드 ========

// 댓글 목록 서버에서 불러오기
fetchReplies();




