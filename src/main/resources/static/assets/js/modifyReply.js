import { BASE_URL } from "./reply.js";
import { fetchInfScrollReplies } from "./getReply.js";

// 수정 이벤트 등록 함수
export function modifyReplyClickEvent(){

    // 수정모드 진입
    //1. 수정하고자 하는 글이 댓글 수정 창에 뜨도록
    document.getElementById('replyData').addEventListener('click', e => {
        e.preventDefault();     //a버튼 링크기능 삭제

        //이벤트 타겟 제한
        if(!e.target.matches('#replyModBtn')) return;

        //console.log('수정모드 진입!');
        //console.log(e.target.closest('.row').firstElementChild.textContent);  //수정하고자 하는 글이 나옴

         // 수정 전 텍스트 읽기
        const replyText = e.target.closest('.row').firstElementChild.textContent;

        // 모달의 textArea에 넣기
        document.getElementById('modReplyText').value = replyText;

        // 댓글번호 구하기
        const rno = e.target.closest('#replyContent').dataset.replyId;

        // 모달에 클릭한 댓글번호 달아놓기
        document.querySelector('.modal').dataset.rno = rno;
    });

 



    // 수정 요청 처리 이벤트
  document.getElementById('replyModBtn').addEventListener('click', e => {
    fetchReplyModify();
  });

}

async function fetchReplyModify() {

  const payload = {
    rno: document.querySelector('.modal').dataset.rno,
    newText: document.getElementById('modReplyText').value,
    bno: document.getElementById('wrap').dataset.bno
  };

  console.log(payload);

  const res = await fetch(BASE_URL, {
    method: 'PUT',
    headers: {
      'content-type': 'application/json'
    },
    body: JSON.stringify(payload)
  });

  if (!res.ok) {
    alert('수정 실패!');
  }

  // 모달 닫기
  document.getElementById('modal-close').click();

  window.scrollTo(0, 500); // 수정 후 페이지 상단으로 이동
  await fetchInfScrollReplies(); 
}