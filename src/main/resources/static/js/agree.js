/*==================================================================
프로젝트명 : 개인정보 동의서
작성지 : 신정호
작성일 : 2023년 12월 06일
용도 : 개인정보 동의서 동의 스크립트
==================================================================*/
$(function(){


    $('#all_area').click(function(){
        if($('#privacyAll').is(':checked')){
            $('#privacyAll').prop('checked',false);
            $('#allCheck').removeClass('active');
            $('.submit_button').removeClass('active');

            $('#privacyFirst').prop('checked',false);
            $('#firstCheck').removeClass('active');
            $('#privacySecond').prop('checked',false);
            $('#secondCheck').removeClass('active');
        } else{
            $('#privacyAll').prop('checked',true);
            $('#allCheck').addClass('active');
            $('#privacyFirst').prop('checked',true);
            $('#firstCheck').addClass('active');
            $('#privacySecond').prop('checked',true);
            $('#secondCheck').addClass('active');
            $('.submit_button').addClass('active');
        }
    });

    $('#first_area').click(function(){
        if($('#privacyFirst').is(':checked')){
            $('#privacyAll').prop('checked',false);
            $('#allCheck').removeClass('active');
            $('#privacyFirst').prop('checked',false);
            $('#firstCheck').removeClass('active');
            $('.submit_button').removeClass('active');
        } else{
            if($('#privacySecond').is(':checked')){
                $('#privacyAll').prop('checked',true);
                $('#allCheck').addClass('active');
                $('.submit_button').addClass('active');
            }
            $('#privacyFirst').prop('checked',true);
            $('#firstCheck').addClass('active');
        }
    });

    $('#second_area').click(function(){
        if($('#privacySecond').is(':checked')){
            $('#privacyAll').prop('checked',false);
            $('#allCheck').removeClass('active');
            $('#privacySecond').prop('checked',false);
            $('#secondCheck').removeClass('active');
            $('.submit_button').removeClass('active');
        } else{
            if($('#privacyFirst').is(':checked')){
                $('#privacyAll').prop('checked',true);
                $('#allCheck').addClass('active');
                $('.submit_button').addClass('active');
            }
            $('#privacySecond').prop('checked',true);
            $('#secondCheck').addClass('active');
        }
    });

    $('#third_area').click(function(){
        if($('#privacyThird').is(':checked')){
            $('#privacyThird').prop('checked',false);
            $('#thirdCheck').removeClass('active');
            $('.choice_form').hide();

        } else{
            $('#privacyThird').prop('checked',true);
            $('#thirdCheck').addClass('active');
            $('.choice_form').show();
        }
    });


    $('#modal_btn').click(function(){
        $('.modal_container').hide();
        location.reload();
    })




})
function onlyNumber(id){
    var replace_text = $(id).val().replace(/[^-0-9]/g, '');
    $(id).val(replace_text);
}

function agreementSubmit(){
    if($('#mPhone').val().length <= 10){
        alert('휴대전화를 확인해주세요.')
        $('#mPhone').focus();
        return false;
    }
    if($('#mName').val().length == 0){
        alert('이름을 입력해주세요.')
        $('#mName').focus();
        return false;
    }


    if(!($('#privacyFirst').is(':checked'))){
        alert('개인정보 수집·이용 내역을 동의해주세요.')
        return false;
    }


    if(!($('#privacySecond').is(':checked'))){
        alert('개인정보 제3자 제공 내역을 동의해주세요.')
        return false;
    }

    if($('#privacyThird').is(':checked')){
        if($('#sPhone').val().length <= 10){
            alert('보호자 휴대전화를 확인해주세요.')
            $('#sPhone').focus();
            return false;
        }
        if($('#sName').val().length == 0){
            alert('보호자 이름을 입력해주세요.')
            $('#sName').focus();
            return false;
        }
    }
    let numberInterval;
    let count = 5;
    $.ajax({
        url : '/createPic',
        method : 'post',
        data : {
            'mPhone' : $('#mPhone').val(),
            'mName' : $('#mName').val(),
            'sPhone' : $('#sPhone').val(),
            'sName' : $('#sName').val(),
        },
        success : function (data){
            $('.modal_container').show();
            $('#mPhone').val('');
            $('#mName').val('');
            $('#sPhone').val('');
            $('#sName').val('');
            $('.submit_button').css('background','#8990a0')
            $('.check_btn').each(function(){
                $(this).css('color','#8990a0');
            })

            numberInterval = setInterval(modalCount, 1000);
        }
    })

    function modalCount(){
        count--;
        $('#auto_number').text(count);
        if(count == 0) {
            $('.modal_container').hide();
            $('#auto_number').text(5);
            clearInterval(numberInterval);
            location.reload();
        };
    }
}



