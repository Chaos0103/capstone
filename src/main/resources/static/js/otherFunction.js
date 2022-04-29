// 특수한 기능만 하는 JS가 아닌 세부적인 동적 웹을 위한 JS 함수 모음 (범용적)

//전체 동의 체크박스 js
function selectAll(selectAll)  {
    const checkboxes 
         = document.getElementsByName('flexRadioDefault');
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = selectAll.checked;
    })
}