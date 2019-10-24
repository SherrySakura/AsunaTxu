//1.设置cookie
function setCookie(cname,cvalue,exdays)
{
    var d = new Date();
    d.setTime(d.getTime()+(exdays*1000*60*60));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
//2.获取cookie
function getCookie(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}

function ajaxGet(url, func) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function (ev) {
        if (request.readyState == 4 && request.status == 200){
            var text = request.responseText;
            var obj = JSON.parse(text);
            func(obj);
        }
    };
    request.open("GET", url);
    request.send();
}

function ajaxPost(url, params, func) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function (ev) {
        if (request.readyState == 4 && request.status == 200){
            var text = request.responseText;
            var obj = JSON.parse(text);
            func(obj);
        }
    };
    request.open("POST", url, true);
    request.send(params);
}

function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

function createRow(data) {
    var row = document.createElement("tr");
    var idCell = document.createElement("td");
    //创建a标签
    var href = "more.html?taskUid=" + data.id;
    var atag = document.createElement("a");
    atag.setAttribute("href", href);
    atag.innerHTML = data.id;
    idCell.appendChild(atag);
    row.appendChild(idCell);

    var typeCell = document.createElement("td");
    typeCell.innerHTML = data.taskType;
    row.appendChild(typeCell);

    var timeCell = document.createElement("td");
    timeCell.innerHTML = data.time;
    row.appendChild(timeCell);

    var lenCell = document.createElement("td");
    lenCell.innerHTML = data.length;
    row.appendChild(lenCell);

    var feeCell = document.createElement("td");
    var f = (data.fee) / 1000000;
    f = f.toFixed(2);
    feeCell.innerHTML = f;
    row.appendChild(feeCell);

    var statuesCell = document.createElement("td");
    var sp = document.createElement("span");
    var st = data.statues;
    if (st === "已处理"){
        sp.setAttribute("class", "item-status delivered");
    } else {
        sp.setAttribute("class", "item-status cancel");
    }
    sp.innerHTML = st;
    statuesCell.appendChild(sp);
    row.appendChild(statuesCell);
    return row;
}

function createRowAll(data) {
    var row = document.createElement("tr");
    var idCell = document.createElement("td");
    //创建a标签
    var atag = document.createElement("a");
    var href = "more.html?taskUid=" + data.id;
    atag.setAttribute("href", href);
    atag.innerHTML = data.id;
    idCell.appendChild(atag);
    row.appendChild(idCell);

    var typeCell = document.createElement("td");
    typeCell.innerHTML = data.taskType;
    row.appendChild(typeCell);

    var timeCell = document.createElement("td");
    timeCell.innerHTML = data.time;
    row.appendChild(timeCell);

    var lenCell = document.createElement("td");
    lenCell.innerHTML = data.length;
    row.appendChild(lenCell);

    var feeCell = document.createElement("td");
    var f = (data.fee) / 1000000;
    f = f.toFixed(2);
    feeCell.innerHTML = f;
    row.appendChild(feeCell);

    var oriCell = document.createElement("td");
    var oriText = data.ori;
    if (oriText.length > 12){
        oriText = oriText.substring(0, 12) + "...";
    }
    oriCell.innerHTML = oriText;
    row.appendChild(oriCell);

    var handledCell = document.createElement("td");
    var handledText = data.handled;
    if (handledText.length > 12){
        handledText = handledText.substring(0, 12) + "...";
    }
    handledCell.innerHTML = handledText;
    row.appendChild(handledCell);

    var statuesCell = document.createElement("td");
    var sp = document.createElement("span");
    var st = data.statues;
    if (st === "已处理"){
        sp.setAttribute("class", "item-status delivered");
    } else {
        sp.setAttribute("class", "item-status cancel");
    }
    sp.innerHTML = st;
    statuesCell.appendChild(sp);
    row.appendChild(statuesCell);
    return row;
}

function getParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return decodeURIComponent(r[2]);
    return null;
}

function createEmptyRow(partern) {
    var row = document.createElement("tr");
    var idCell = document.createElement("td");
    var atag = document.createElement("a");
    atag.setAttribute("href", "#");
    atag.innerHTML = partern;
    idCell.appendChild(atag);
    row.appendChild(idCell);

    var typeCell = document.createElement("td");
    typeCell.innerHTML = partern;
    row.appendChild(typeCell);

    var timeCell = document.createElement("td");
    timeCell.innerHTML = partern;
    row.appendChild(timeCell);

    var lenCell = document.createElement("td");
    lenCell.innerHTML = partern;
    row.appendChild(lenCell);

    var feeCell = document.createElement("td");
    feeCell.innerHTML = partern;
    row.appendChild(feeCell);

    var statuesCell = document.createElement("td");
    statuesCell.innerHTML = partern;
    row.appendChild(statuesCell);
    return row;
}

function createEmptyDetail(partern) {
    var row = document.createElement("tr");
    var idCell = document.createElement("td");
    var atag = document.createElement("a");
    atag.setAttribute("href", "#");
    atag.innerHTML = partern;
    idCell.appendChild(atag);
    row.appendChild(idCell);

    var typeCell = document.createElement("td");
    typeCell.innerHTML = partern;
    row.appendChild(typeCell);

    var timeCell = document.createElement("td");
    timeCell.innerHTML = partern;
    row.appendChild(timeCell);

    var lenCell = document.createElement("td");
    lenCell.innerHTML = partern;
    row.appendChild(lenCell);

    var feeCell = document.createElement("td");
    feeCell.innerHTML = partern;
    row.appendChild(feeCell);

    var oriCell = document.createElement("td");
    oriCell.innerHTML = partern;
    row.appendChild(oriCell);

    var handledCell = document.createElement("td");
    handledCell.innerHTML = partern;
    row.appendChild(handledCell);

    var statuesCell = document.createElement("td");
    statuesCell.innerHTML = partern;
    row.appendChild(statuesCell);
    return row;
}