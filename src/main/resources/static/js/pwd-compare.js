function compare(){
    var pwd_1 = document.querySelector('#pwd').value;
    var pwd_2 = document.querySelector('#pwd-compare').value;

    if(pwd_1 != pwd_2){
        alert("비밀번호가 다릅니다.");
        return false;
    }
    return true;
}