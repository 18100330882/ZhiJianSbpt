//计数器
var bitemp = 0;
// 拖拽对象 
// 参见：http://blog.sina.com.cn/u/4702ecbe010007pe 
var ie = document.all;
var nn6 = document.getElementById && !document.all;
var isdrag = false;
var y, x;
var oDragObj;
var PreHeight;
var PreWidth;
var Preblock1Height;
var Preblock1Width;

//鼠标移动事件
function moveMouse(e) {
    if (isdrag) {
        oDragObj.style.top = (nn6 ? nTY + e.clientY - y : nTY + event.clientY - y) + "px";
        oDragObj.style.left = (nn6 ? nTX + e.clientX - x : nTX + event.clientX - x) + "px";
        return false;
    }
}

function initDrag(e) {
    var oDragHandle = nn6 ? e.target : event.srcElement;
    var topElement = "HTML";
    while (oDragHandle.tagName != topElement && oDragHandle.className != "dragAble") {
        oDragHandle = nn6 ? oDragHandle.parentNode : oDragHandle.parentElement;
    }
    if (oDragHandle.className == "dragAble") {
        isdrag = true;
        oDragObj = oDragHandle;
        nTY = parseInt(oDragObj.style.top + 0);
        y = nn6 ? e.clientY : event.clientY;
        nTX = parseInt(oDragObj.style.left + 0);
        x = nn6 ? e.clientX : event.clientX;
        document.onmousemove = moveMouse;
        return false;
    }
}

document.onmousedown = initDrag;
document.onmouseup = new Function("isdrag=false");

function MM_reloadPage(init) { //reloads the window if Nav4 resized 
    if (init == true) with (navigator) {
        if ((appName == "Netscape") && (parseInt(appVersion) == 4)) {
            document.MM_pgW = innerWidth;
            document.MM_pgH = innerHeight;
            onresize = MM_reloadPage;
        }
    }
    else if (innerWidth != document.MM_pgW || innerHeight != document.MM_pgH) location.reload();
}

MM_reloadPage(true);


//移动
function clickMove(s) {
    if (s == "up") {
        block1.style.top = parseInt(block1.style.top) - 100;
    } else if (s == "down") {
        block1.style.top = parseInt(block1.style.top) + 100;
    } else if (s == "left") {
        block1.style.left = parseInt(block1.style.left) - 100;
    } else if (s == "right") {
        block1.style.left = parseInt(block1.style.left) + 100;
    }
}

//缩小
function smallit() {
    debugger;
    if (bitemp > 0) {
        var height1 = images1.height;
        var width1 = images1.width;
        $("#images1").height(height1 / 1.2);
        $("#images1").width(width1 / 1.2);
//        images1.height = height1 / 1.2;
//        images1.width = width1 / 1.2;
        bitemp--;
    }
}

//放大
function bigit() {
    debugger;
    if (bitemp < 10) {
        var height1 = images1.height;
        var width1 = images1.width;
        $("#images1").height(height1 * 1.2);
        $("#images1").width(width1 * 1.2);
//        images1.height = height1 * 1.2;
//        images1.width = width1 * 1.2;
        bitemp++;
    }
}

//还原
function realsize() {
    debugger;
    //$("#images1").height(ScreenHeight - 50 - 50);//annotation by hyh 
    //$("#images1").width(ScreenWidth- 25 - 50);//annotation by hyh
    $("#images1").height(PreHeight);
    $("#images1").width(PreWidth);
    $("#images1").rotate(0);
    block1.style.left = 40; //Preblock1Width; 40
    block1.style.top = 5; //Preblock1Height; 5
    bitemp = 0;
}

//旋转
function playImg() {
    $("#images1").rotate(90);
}

//设置block1的高度和宽度
$(function SetPosition() {
    debugger;
    ScreenHeight = window.screen.height;
    ScreenWidth = window.screen.width;
    var h = ($(document).height() - images1.height) / 2;
    var w = ($(document).width() - images1.width) / 2;
    if ($("#images1").height() > ScreenHeight)
        $("#images1").height(ScreenHeight - 50 - 50);
    if ($("#images1").width() > ScreenWidth)
        $("#images1").width(ScreenWidth - 25 - 50);
    PreHeight = $("#images1").height();
    PreWidth = $("#images1").width();
    Preblock1Height = h;
    Preblock1Width = w;
})