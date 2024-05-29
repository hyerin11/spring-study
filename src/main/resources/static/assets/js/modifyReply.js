

// 수정 이벤트 등록 함수
export function modifyReplyClickEvent(){

    //1. 수정하고자 하는 글이 댓글 수정 창에 뜨도록
    document.getElementById('replyData').addEventListener('click', e => {
        e.preventDefault(); //a버튼 링크기능 삭제

        //이벤트 타겟 제한
        if(!e.target.matches('#replyModBtn')) return;

        //console.log('수정모드 진입!');
        //console.log(e.target.closest('.row').firstElementChild.textContent);  //수정하고자 하는 글이 나옴

        //수정 전 텍스트 읽기
        const replyText = e.target.closest('.row').firstElementChild.textContent;

        //모달의 textArea에 넣기
        document.getElementById('modReplyText').value = replyText;
    })
}