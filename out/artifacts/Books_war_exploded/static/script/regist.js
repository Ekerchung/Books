    //頁面加載完成後
    // 1.綁定事件
    $(function (){
    $("#registbuttom").click(function (){
        // 2.驗證用戶名稱
        // 2.1 獲取輸入之username
        var usernameText = $("#username").val();
        // 2.2 創建正則表達式規則
        var usernamePatt = /^\w{5,12}$/;
        // 2.3 驗證輸入名稱是否符合規則，若不符合則提示錯誤訊息
        if (!usernamePatt.test(usernameText)){
            $("span.errorMsg").text("帳號格式有誤，請重新輸入");
            return false;
        }
        // 3.驗證用戶密碼
        // 3.1 獲取輸入的密碼及確認密碼，並創建驗證之正則表達式
        var passwordTest = $("#password").val();
        var checkpasswordTest = $("#checkpassword").val();
        var passwordPatt = /^\w{5,12}$/
        // 3.2 驗證密碼是否合法，若不合法則提示錯誤訊息
        if(!passwordPatt.test(passwordTest)){
            $("span.errorMsg").text("密碼格式不合法");
            return false
        }
        // 3.3 驗證確認密碼和密碼是否相同
        if(passwordTest != checkpasswordTest){
            $("span.errorMsg").text(" 確認密碼和密碼不一致，請重新輸入");
            return false;
        }
        // 4.驗證信箱
        // 4.1 獲取輸入信箱，並創建驗證之正則表達式
        var emailTest = $("#email").val();
        var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
        // 4.2 驗證信箱是否合法
        if(!emailPatt.test(emailTest)){
            $("span.errorMsg").text("電子信箱格式不合法")
            return false;
        }
        // 5.驗證碼(待續)
    })
});
