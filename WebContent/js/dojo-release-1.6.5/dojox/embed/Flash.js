/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["dojox.embed.Flash"]){
dojo._hasResource["dojox.embed.Flash"]=true;
dojo.provide("dojox.embed.Flash");
(function(){
function _1(_2){
return String(_2).replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/"/g,"&quot;").replace(/'/g,"&apos;");
};
var _3,_4;
var _5=9;
var _6="dojox-embed-flash-",_7=0;
var _8={expressInstall:false,width:320,height:240,swLiveConnect:"true",allowScriptAccess:"sameDomain",allowNetworking:"all",style:null,redirect:null};
function _9(_a){
_a=dojo.delegate(_8,_a);
if(!("path" in _a)){
console.error("dojox.embed.Flash(ctor):: no path reference to a Flash movie was provided.");
return null;
}
if(!("id" in _a)){
_a.id=(_6+_7++);
}
return _a;
};
if(dojo.isIE){
_3=function(_b){
_b=_9(_b);
if(!_b){
return null;
}
var p;
var _c=_b.path;
if(_b.vars){
var a=[];
for(p in _b.vars){
a.push(encodeURIComponent(p)+"="+encodeURIComponent(_b.vars[p]));
}
_b.params.FlashVars=a.join("&");
delete _b.vars;
}
var s="<object id=\""+_1(String(_b.id))+"\" "+"classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" "+"width=\""+_1(String(_b.width))+"\" "+"height=\""+_1(String(_b.height))+"\""+((_b.style)?" style=\""+_1(String(_b.style))+"\"":"")+">"+"<param name=\"movie\" value=\""+_1(String(_c))+"\" />";
if(_b.params){
for(p in _b.params){
s+="<param name=\""+_1(p)+"\" value=\""+_1(String(_b.params[p]))+"\" />";
}
}
s+="</object>";
return {id:_b.id,markup:s};
};
_4=(function(){
var _d=10,_e=null;
while(!_e&&_d>7){
try{
_e=new ActiveXObject("ShockwaveFlash.ShockwaveFlash."+_d--);
}
catch(e){
}
}
if(_e){
var v=_e.GetVariable("$version").split(" ")[1].split(",");
return {major:(v[0]!=null)?parseInt(v[0]):0,minor:(v[1]!=null)?parseInt(v[1]):0,rev:(v[2]!=null)?parseInt(v[2]):0};
}
return {major:0,minor:0,rev:0};
})();
dojo.addOnUnload(function(){
var _f=function(){
};
var _10=dojo.query("object").reverse().style("display","none").forEach(function(i){
for(var p in i){
if((p!="FlashVars")&&dojo.isFunction(i[p])){
try{
i[p]=_f;
}
catch(e){
}
}
}
});
});
}else{
_3=function(_11){
_11=_9(_11);
if(!_11){
return null;
}
var p;
var _12=_11.path;
if(_11.vars){
var a=[];
for(p in _11.vars){
a.push(encodeURIComponent(p)+"="+encodeURIComponent(_11.vars[p]));
}
_11.params.flashVars=a.join("&");
delete _11.vars;
}
var s="<embed type=\"application/x-shockwave-flash\" "+"src=\""+_1(String(_12))+"\" "+"id=\""+_1(String(_11.id))+"\" "+"width=\""+_1(String(_11.width))+"\" "+"height=\""+_1(String(_11.height))+"\""+((_11.style)?" style=\""+_1(String(_11.style))+"\" ":"")+"pluginspage=\""+window.location.protocol+"//www.adobe.com/go/getflashplayer\" ";
if(_11.params){
for(p in _11.params){
s+=" "+_1(p)+"=\""+_1(String(_11.params[p]))+"\"";
}
}
s+=" />";
return {id:_11.id,markup:s};
};
_4=(function(){
var _13=navigator.plugins["Shockwave Flash"];
if(_13&&_13.description){
var v=_13.description.replace(/([a-zA-Z]|\s)+/,"").replace(/(\s+r|\s+b[0-9]+)/,".").split(".");
return {major:(v[0]!=null)?parseInt(v[0]):0,minor:(v[1]!=null)?parseInt(v[1]):0,rev:(v[2]!=null)?parseInt(v[2]):0};
}
return {major:0,minor:0,rev:0};
})();
}
dojox.embed.Flash=function(_14,_15){
if(location.href.toLowerCase().indexOf("file://")>-1){
throw new Error("dojox.embed.Flash can't be run directly from a file. To instatiate the required SWF correctly it must be run from a server, like localHost.");
}
this.available=dojox.embed.Flash.available;
this.minimumVersion=_14.minimumVersion||_5;
this.id=null;
this.movie=null;
this.domNode=null;
if(_15){
_15=dojo.byId(_15);
}
setTimeout(dojo.hitch(this,function(){
if(_14.expressInstall||this.available&&this.available>=this.minimumVersion){
if(_14&&_15){
this.init(_14,_15);
}else{
this.onError("embed.Flash was not provided with the proper arguments.");
}
}else{
if(!this.available){
this.onError("Flash is not installed.");
}else{
this.onError("Flash version detected: "+this.available+" is out of date. Minimum required: "+this.minimumVersion);
}
}
}),100);
};
dojo.extend(dojox.embed.Flash,{onReady:function(_16){
console.warn("embed.Flash.movie.onReady:",_16);
},onLoad:function(_17){
console.warn("embed.Flash.movie.onLoad:",_17);
},onError:function(msg){
},_onload:function(){
clearInterval(this._poller);
delete this._poller;
delete this._pollCount;
delete this._pollMax;
this.onLoad(this.movie);
},init:function(_18,_19){
this.destroy();
_19=dojo.byId(_19||this.domNode);
if(!_19){
throw new Error("dojox.embed.Flash: no domNode reference has been passed.");
}
var p=0,_1a=false;
this._poller=null;
this._pollCount=0;
this._pollMax=15;
this.pollTime=100;
if(dojox.embed.Flash.initialized){
this.id=dojox.embed.Flash.place(_18,_19);
this.domNode=_19;
setTimeout(dojo.hitch(this,function(){
this.movie=this.byId(this.id,_18.doc);
this.onReady(this.movie);
this._poller=setInterval(dojo.hitch(this,function(){
try{
p=this.movie.PercentLoaded();
}
catch(e){
console.warn("this.movie.PercentLoaded() failed");
}
if(p==100){
this._onload();
}else{
if(p==0&&this._pollCount++>this._pollMax){
clearInterval(this._poller);
throw new Error("Building SWF failed.");
}
}
}),this.pollTime);
}),1);
}
},_destroy:function(){
try{
this.domNode.removeChild(this.movie);
}
catch(e){
}
this.id=this.movie=this.domNode=null;
},destroy:function(){
if(!this.movie){
return;
}
var _1b=dojo.delegate({id:true,movie:true,domNode:true,onReady:true,onLoad:true});
for(var p in this){
if(!_1b[p]){
delete this[p];
}
}
if(this._poller){
dojo.connect(this,"onLoad",this,"_destroy");
}else{
this._destroy();
}
},byId:function(_1c,doc){
doc=doc||document;
if(doc.embeds[_1c]){
return doc.embeds[_1c];
}
if(doc[_1c]){
return doc[_1c];
}
if(window[_1c]){
return window[_1c];
}
if(document[_1c]){
return document[_1c];
}
return null;
}});
dojo.mixin(dojox.embed.Flash,{minSupported:8,available:_4.major,supported:(_4.major>=_4.required),minimumRequired:_4.required,version:_4,initialized:false,onInitialize:function(){
dojox.embed.Flash.initialized=true;
},__ie_markup__:function(_1d){
return _3(_1d);
},proxy:function(obj,_1e){
dojo.forEach((dojo.isArray(_1e)?_1e:[_1e]),function(_1f){
this[_1f]=dojo.hitch(this,function(){
return (function(){
return eval(this.movie.CallFunction("<invoke name=\""+_1f+"\" returntype=\"javascript\">"+"<arguments>"+dojo.map(arguments,function(_20){
return __flash__toXML(_20);
}).join("")+"</arguments>"+"</invoke>"));
}).apply(this,arguments||[]);
});
},obj);
}});
dojox.embed.Flash.place=function(_21,_22){
var o=_3(_21);
_22=dojo.byId(_22);
if(!_22){
_22=dojo.doc.createElement("div");
_22.id=o.id+"-container";
dojo.body().appendChild(_22);
}
if(o){
_22.innerHTML=o.markup;
return o.id;
}
return null;
};
dojox.embed.Flash.onInitialize();
})();
}
