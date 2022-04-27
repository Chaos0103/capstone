function postcode() {
    new daum.Postcode({
        oncomplete:function(data){
            document.querySelector('#post-number').value = data.zonecode;
            document.querySelector('#address').value = data.address;
        }
    }).open();
}