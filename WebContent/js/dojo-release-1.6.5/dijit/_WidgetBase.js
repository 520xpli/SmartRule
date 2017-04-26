/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["dijit._WidgetBase"]){
dojo._hasResource["dijit._WidgetBase"]=true;
dojo.provide("dijit._WidgetBase");
dojo.require("dijit._base.manager");
dojo.require("dojo.Stateful");
(function(){
function _1(a,b){
return a===b||(a!==a&&b!==b);
};
dojo.declare("dijit._WidgetBase",dojo.Stateful,{id:"",lang:"",dir:"","class":"",style:"",title:"",tooltip:"",baseClass:"",srcNodeRef:null,domNode:null,containerNode:null,attributeMap:{id:"",dir:"",lang:"","class":"",style:"",title:""},_blankGif:(dojo.config.blankGif||dojo.moduleUrl("dojo","resources/blank.gif")).toString(),postscript:function(_2,_3){
this.create(_2,_3);
},create:function(_4,_5){
this.srcNodeRef=dojo.byId(_5);
this._connects=[];
this._subscribes=[];
if(this.srcNodeRef&&(typeof this.srcNodeRef.id=="string")){
this.id=this.srcNodeRef.id;
}
if(_4){
this.params=_4;
dojo._mixin(this,_4);
}
this.postMixInProperties();
if(!this.id){
this.id=dijit.getUniqueId(this.declaredClass.replace(/\./g,"_"));
}
dijit.registry.add(this);
this.buildRendering();
if(this.domNode){
this._applyAttributes();
var _6=this.srcNodeRef;
if(_6&&_6.parentNode&&this.domNode!==_6){
_6.parentNode.replaceChild(this.domNode,_6);
}
}
if(this.domNode){
this.domNode.setAttribute("widgetId",this.id);
}
this.postCreate();
if(this.srcNodeRef&&!this.srcNodeRef.parentNode){
delete this.srcNodeRef;
}
this._created=true;
},_applyAttributes:function(){
var _7=function(_8,_9){
if((_9.params&&_8 in _9.params)||_9[_8]){
_9.set(_8,_9[_8]);
}
};
for(var _a in this.attributeMap){
_7(_a,this);
}
dojo.forEach(this._getSetterAttributes(),function(a){
if(!(a in this.attributeMap)){
_7(a,this);
}
},this);
},_getSetterAttributes:function(){
var _b=this.constructor;
if(!_b._setterAttrs){
var r=(_b._setterAttrs=[]),_c,_d=_b.prototype;
for(var _e in _d){
if(dojo.isFunction(_d[_e])&&(_c=_e.match(/^_set([a-zA-Z]*)Attr$/))&&_c[1]){
r.push(_c[1].charAt(0).toLowerCase()+_c[1].substr(1));
}
}
}
return _b._setterAttrs;
},postMixInProperties:function(){
},buildRendering:function(){
if(!this.domNode){
this.domNode=this.srcNodeRef||dojo.create("div");
}
if(this.baseClass){
var _f=this.baseClass.split(" ");
if(!this.isLeftToRight()){
_f=_f.concat(dojo.map(_f,function(_10){
return _10+"Rtl";
}));
}
dojo.addClass(this.domNode,_f);
}
},postCreate:function(){
},startup:function(){
this._started=true;
},destroyRecursive:function(_11){
this._beingDestroyed=true;
this.destroyDescendants(_11);
this.destroy(_11);
},destroy:function(_12){
this._beingDestroyed=true;
this.uninitialize();
var d=dojo,dfe=d.forEach,dun=d.unsubscribe;
dfe(this._connects,function(_13){
dfe(_13,d.disconnect);
});
dfe(this._subscribes,function(_14){
dun(_14);
});
dfe(this._supportingWidgets||[],function(w){
if(w.destroyRecursive){
w.destroyRecursive();
}else{
if(w.destroy){
w.destroy();
}
}
});
this.destroyRendering(_12);
dijit.registry.remove(this.id);
this._destroyed=true;
},destroyRendering:function(_15){
if(this.bgIframe){
this.bgIframe.destroy(_15);
delete this.bgIframe;
}
if(this.domNode){
if(_15){
dojo.removeAttr(this.domNode,"widgetId");
}else{
dojo.destroy(this.domNode);
}
delete this.domNode;
}
if(this.srcNodeRef){
if(!_15){
dojo.destroy(this.srcNodeRef);
}
delete this.srcNodeRef;
}
},destroyDescendants:function(_16){
dojo.forEach(this.getChildren(),function(_17){
if(_17.destroyRecursive){
_17.destroyRecursive(_16);
}
});
},uninitialize:function(){
return false;
},_setClassAttr:function(_18){
var _19=this[this.attributeMap["class"]||"domNode"];
dojo.replaceClass(_19,_18,this["class"]);
this._set("class",_18);
},_setStyleAttr:function(_1a){
var _1b=this[this.attributeMap.style||"domNode"];
if(dojo.isObject(_1a)){
dojo.style(_1b,_1a);
}else{
if(_1b.style.cssText){
_1b.style.cssText+="; "+_1a;
}else{
_1b.style.cssText=_1a;
}
}
this._set("style",_1a);
},_attrToDom:function(_1c,_1d){
var _1e=this.attributeMap[_1c];
dojo.forEach(dojo.isArray(_1e)?_1e:[_1e],function(_1f){
var _20=this[_1f.node||_1f||"domNode"];
var _21=_1f.type||"attribute";
switch(_21){
case "attribute":
if(dojo.isFunction(_1d)){
_1d=dojo.hitch(this,_1d);
}
var _22=_1f.attribute?_1f.attribute:(/^on[A-Z][a-zA-Z]*$/.test(_1c)?_1c.toLowerCase():_1c);
dojo.attr(_20,_22,_1d);
break;
case "innerText":
_20.innerHTML="";
_20.appendChild(dojo.doc.createTextNode(_1d));
break;
case "innerHTML":
_20.innerHTML=_1d;
break;
case "class":
dojo.replaceClass(_20,_1d,this[_1c]);
break;
}
},this);
},get:function(_23){
var _24=this._getAttrNames(_23);
return this[_24.g]?this[_24.g]():this[_23];
},set:function(_25,_26){
if(typeof _25==="object"){
for(var x in _25){
this.set(x,_25[x]);
}
return this;
}
var _27=this._getAttrNames(_25);
if(this[_27.s]){
var _28=this[_27.s].apply(this,Array.prototype.slice.call(arguments,1));
}else{
if(_25 in this.attributeMap){
this._attrToDom(_25,_26);
}
this._set(_25,_26);
}
return _28||this;
},_attrPairNames:{},_getAttrNames:function(_29){
var apn=this._attrPairNames;
if(apn[_29]){
return apn[_29];
}
var uc=_29.charAt(0).toUpperCase()+_29.substr(1);
return (apn[_29]={n:_29+"Node",s:"_set"+uc+"Attr",g:"_get"+uc+"Attr"});
},_set:function(_2a,_2b){
var _2c=this[_2a];
this[_2a]=_2b;
if(this._watchCallbacks&&this._created&&!_1(_2b,_2c)){
this._watchCallbacks(_2a,_2c,_2b);
}
},toString:function(){
return "[Widget "+this.declaredClass+", "+(this.id||"NO ID")+"]";
},getDescendants:function(){
return this.containerNode?dojo.query("[widgetId]",this.containerNode).map(dijit.byNode):[];
},getChildren:function(){
return this.containerNode?dijit.findWidgets(this.containerNode):[];
},connect:function(obj,_2d,_2e){
var _2f=[dojo._connect(obj,_2d,this,_2e)];
this._connects.push(_2f);
return _2f;
},disconnect:function(_30){
for(var i=0;i<this._connects.length;i++){
if(this._connects[i]==_30){
dojo.forEach(_30,dojo.disconnect);
this._connects.splice(i,1);
return;
}
}
},subscribe:function(_31,_32){
var _33=dojo.subscribe(_31,this,_32);
this._subscribes.push(_33);
return _33;
},unsubscribe:function(_34){
for(var i=0;i<this._subscribes.length;i++){
if(this._subscribes[i]==_34){
dojo.unsubscribe(_34);
this._subscribes.splice(i,1);
return;
}
}
},isLeftToRight:function(){
return this.dir?(this.dir=="ltr"):dojo._isBodyLtr();
},placeAt:function(_35,_36){
if(_35.declaredClass&&_35.addChild){
_35.addChild(this,_36);
}else{
dojo.place(this.domNode,_35,_36);
}
return this;
},defer:function(fcn,_37){
var _38=setTimeout(dojo.hitch(this,function(){
_38=null;
if(!this._destroyed){
dojo.hitch(this,fcn)();
}
}),_37||0);
return {remove:function(){
if(_38){
clearTimeout(_38);
_38=null;
}
return null;
}};
}});
})();
}
