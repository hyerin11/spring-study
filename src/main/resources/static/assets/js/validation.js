
// 회원가입 입력 검증 처리

// 계정 중복검사 비동기 요청 보내기
async function fetchDuplicateCheck(idValue) {

    const res = await fetch(`http://localhost:8383/members/check?type=account&keyword=${idValue}`);
    const flag = await res.json();
  
    idFlag = flag;
  }
  
  // 계정 입력 검증
  const $idInput = document.getElementById('user_id');
  let idFlag = false;
  
  $idInput.addEventListener('keyup', async e => {
  
    // 아이디 검사 정규표현식
    const accountPattern = /^[a-zA-Z0-9]{4,14}$/;
  
    // 입력값 읽어오기
    const idValue = $idInput.value;
    // console.log(idValue);
  
    // 검증 메시지를 입력할 span
    const $idChk = document.getElementById('idChk');
  
    if (idValue.trim() === '') {
      $idInput.style.borderColor = 'red';
      $idChk.innerHTML = '<b class="warning">[아이디는 필수로 입력해 주세요.]</b>';
    } else if (!accountPattern.test(idValue)) {
      $idInput.style.borderColor = 'red';
      $idChk.innerHTML = '<b class="warning">[아이디는 영문, 숫자로 4~14글자 이내로 입력하세요!]</b>';
    } else {
  
      // 아이디 중복검사
      await fetchDuplicateCheck(idValue);
  
      if (idFlag) {
        $idInput.style.borderColor = 'red';
        $idChk.innerHTML = '<b class="warning">[아이디가 중복되었습니다. 다른 아이디를 사용하세요!]</b>';
      } else {
        $idInput.style.borderColor = 'skyblue';
        $idChk.innerHTML = '<b class="success">[사용 가능한 아이디입니다.]</b>';
      }
    }
  
  });



  // 비밀번호 
    // 계정 중복검사 비동기 요청 보내기
async function fetchDuplicateCheck(idValue) {

    const res = await fetch(`http://localhost:8383/members/check?type=account&keyword=${pwValue}`);
    const flag = await res.json();
  
    pwFlag = flag;
  }
  
  // 계정 입력 검증
  const $pwInput = document.getElementById('password'); //첫 번쨰 비밀번호 입력
  //const $pwCheckInput = document.getElementById('password_check'); //비밀번호 재확인 입력
  //let pwFlag = false;
  
  
  $pwInput.addEventListener('keyup', async e => {
  
    // 패스워드 검사 정규표현식
    const passwordPattern = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;
  
    // 첫 번째 패스워드 입력값 읽어오기
    const pwValue = $pwInput.value;
    // const pwChkValue = $pwCheckInput.value;
        console.log(pwValue);
  
    // 검증 메시지를 입력할 span
    const $pwChk = document.getElementById('pwChk');
    // const $pwChk2 = document.getElementById('pw_Chk2');
  
    if (pwValue.trim() === '') {
      $idInput.style.borderColor = 'red';
      $pwChk.innerHTML = '<b class="warning">[패스워드는 필수로 입력해야 합니다.]</b>';
    } else if (!passwordPattern.test(pwValue)) {
      $idInput.style.borderColor = 'red';
      $pwChk.innerHTML = '<b class="warning">[패스워드는 영문과 특수문자를 포함한 최소 8자 이상이어야 합니다!]</b>';
    }else {
        $pwChk.innerHTML = '<b class="success">[사용 가능한 비밀번호 입니다]</b>';
    }
  
  });


  //비밀번호 재확인
  async function fetchDuplicateCheck(pwChkValue) {

    const res = await fetch(`http://localhost:8383/members/check?type=account&keyword=${pwChkValue}`);
    const flag = await res.json();
  
    pwFlag2 = flag;
  }
  
  // 계정 입력 검증
  const $pwCheckInput = document.getElementById('password_check'); //비밀번호 재확인 입력

  //let pwFlag = false;
  
  
  $pwInput.addEventListener('keyup', async e => {
  
  
    // 첫 번째 패스워드 입력값 읽어오기
     const pwChkValue = $pwCheckInput.value;
     const pwValue = $pwInput.value;
        console.log(pwChkValue);
  
    // 검증 메시지를 입력할 span
    const $pwChk2 = document.getElementById('pwChk2');
    
    if ($pwInput.value === pwChkValue) {
        $idInput.style.borderColor = 'skyblue';
        $pwChk2.innerHTML = '<b class="success">[비밀번호가 일치합니다]</b>';
    } else {
        $idInput.style.borderColor = 'red';
        $pwChk2.innerHTML = '<b class="warning">[패스워드가 일치하지 않습니다.]</b>';
    }
  
  });




  // 이름 입력하기
// 계정 중복검사 비동기 요청 보내기
async function fetchDuplicateCheck(idValue) {

    const res = await fetch(`http://localhost:8383/members/check?type=account&keyword=${nameValue}`);
    const flag = await res.json();
  
    idFlag = flag;
  }
  
  // 계정 입력 검증
  const $nameInput = document.getElementById('user_name');
  let namdFlag = false;
  
  $nameInput.addEventListener('keyup', async e => {
  
    
    // 이름 검사 정규표현식
    const namePattern = /^[가-힣]+$/;
  
    // 입력값 읽어오기
    const nameValue = $nameInput.value;
     console.log(nameValue);
  
    // 검증 메시지를 입력할 span
    const $nameChk = document.getElementById('nameChk');
  
    if (nameValue.trim() === '') {
      $nameInput.style.borderColor = 'red';
      $nameChk.innerHTML = '<b class="warning">[이름을 입력해 주세요.]</b>';
    } else if(!namePattern.test(nameValue)) {
      $nameInput.style.borderColor = 'red';
      $nameChk.innerHTML = '<b class="warning">[최대 6글자까지 가능합니다.]</b>';
    } else {
        $nameInput.style.borderColor = 'skyblue';
        $nameChk.innerHTML = '<b class="success">[이름이 등록되었습니다.]</b>';
    }
  
  });



  
  // 계정 중복검사 비동기 요청 보내기
async function fetchDuplicateCheck(emailValue) {

    //const res = await fetch(`http://localhost:8383/members/check?type=account&keyword=${emailValue}`);
    //const flag = await res.json();
  
   // emailFlag = flag;
  }
  
  // 계정 입력 검증
  const $emailInput = document.getElementById('emailChk');
  let emailFlag = false;
  
  $emailInput.addEventListener('keyup', async e => {
  
    // 이메일 검사 정규표현식
    const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
  
    // 입력값 읽어오기
    const emailValue = $emailInput.value;
    console.log(emailValue);
  
    // 검증 메시지를 입력할 span
    const $emailChk = document.getElementById('emailChk');
  
    if (emailValue.trim() === '') {
      $emailInput.style.borderColor = 'red';
      $emailChk.innerHTML = '<b class="warning">[이메일을 필수로 입력해 주세요.]</b>';
    } else if (!emailPattern.test(emailValue)) {
      $emailInput.style.borderColor = 'red';
      $emailChk.innerHTML = '<b class="warning">[이메일 형식을 지켜주세요]</b>';
    } else {
  
        $emailInput.style.borderColor = 'skyblue';
        $emailChk.innerHTML = '<b class="success">[사용 가능한 이메일입니다.]</b>';

    //   // 아이디 중복검사
    //   await fetchDuplicateCheck(emailValue);
  
    //   if (emailFlag) {
    //     $emailInput.style.borderColor = 'red';
    //     $emailChk.innerHTML = '<b class="warning">[이미 사용중인 이메일 입니다.]</b>';
    //   } else {
    //     $emailInput.style.borderColor = 'skyblue';
    //     $emailChk.innerHTML = '<b class="success">[사용 가능한 이메일입니다.]</b>';
    //   }
    }
  
  });