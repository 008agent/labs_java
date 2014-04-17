function foo(element){
    alert('foo() called by ' + element);
}

function browser_info(){
    var is_firefox = navigator.userAgent.search('Firefox/')==-1 ? false : true;
    var is_opera   = navigator.userAgent.search('OPR/')==-1 ? false : true;
    var is_ie_new  = navigator.userAgent.search('.NET')==-1 ? false : true;
        if(is_firefox){
            return 'firefox';
        }
        if(is_opera){
            return 'opera';
        }
        if(is_ie_new){
            return 'iexplorer';
        }
        return 'other';
}

function validateY(){
    var tmpY = document.getElementById('Y').value;
    var numY = Number(tmpY);
    if(tmpY.length===0){
        alert('nothing entered in Y field');
        return false;
    }
    if(isNaN(tmpY)){
        alert('NaN value entered in Y field');
        return false;
    }
    if(numY<=-5||numY>=3){
        alert('Y must be (-5..3), entered Y=' + tmpY);
        return false;
    }
    return true;
}

function validateR(){
    var tmpR = document.getElementById('R').value;
        if(tmpR.length===0){
        alert('R is undefined');
        return false;
    }
    return true;
}

function setX(elem_value){
    document.getElementById('X').value = elem_value.value;
}
function setY(elem_value){
    document.getElementById('Y').value = elem_value.value;
}
function setR(elem_value){
    document.getElementById('R').value = elem_value.value;
}

function checkbrowser(){
    if(browser_info()!=='firefox'){
        return false;
    }
    return true;
}

function get_element_x(e,element){
    var X = e.offsetX ? (e.offsetX) : (e.pageX - element.offsetLeft);
    var R = document.getElementById('R').value;
    X = X - 110;
    X = X/80;
    X = X*R;
    return X;
}

function get_element_y(e,element){
    var Y = e.offsetY ? (e.offsetY) : (e.pageY - element.offsetTop);
    var R = document.getElementById('R').value;
    Y = Y-110;
    Y *= -1;
    Y *= R;
    Y/= 80;
    return Y;  
}

function img_click(e,element){
    var x = get_element_x(e,element);
    var y = get_element_y(e,element);
    if(validateR())
    {
        document.getElementById('X').value = x;
        document.getElementById('Y').value = y;
        document.getElementById('form_main').submit();
        //alert(document.getElementById('X').value + ' ' + document.getElementById('Y').value + ' ' + document.getElementById('R').value);
    }
}

function submit_form(){
if(validateR()&&validateY())
    document.getElementById('form_main').submit();
    }