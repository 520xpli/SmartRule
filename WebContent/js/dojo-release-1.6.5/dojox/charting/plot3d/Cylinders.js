/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["dojox.charting.plot3d.Cylinders"]){
dojo._hasResource["dojox.charting.plot3d.Cylinders"]=true;
dojo.provide("dojox.charting.plot3d.Cylinders");
dojo.require("dojox.charting.plot3d.Base");
(function(){
var _1=function(a,f,o){
a=typeof a=="string"?a.split(""):a;
o=o||dojo.global;
var z=a[0];
for(var i=1;i<a.length;z=f.call(o,z,a[i++])){
}
return z;
};
dojo.declare("dojox.charting.plot3d.Cylinders",dojox.charting.plot3d.Base,{constructor:function(_2,_3,_4){
this.depth="auto";
this.gap=0;
this.data=[];
this.material={type:"plastic",finish:"shiny",color:"lime"};
this.outline=null;
if(_4){
if("depth" in _4){
this.depth=_4.depth;
}
if("gap" in _4){
this.gap=_4.gap;
}
if("material" in _4){
var m=_4.material;
if(typeof m=="string"||m instanceof dojo.Color){
this.material.color=m;
}else{
this.material=m;
}
}
if("outline" in _4){
this.outline=_4.outline;
}
}
},getDepth:function(){
if(this.depth=="auto"){
var w=this.width;
if(this.data&&this.data.length){
w=w/this.data.length;
}
return w-2*this.gap;
}
return this.depth;
},generate:function(_5,_6){
if(!this.data){
return this;
}
var _7=this.width/this.data.length,_8=0,_9=this.height/_1(this.data,Math.max);
if(!_6){
_6=_5.view;
}
for(var i=0;i<this.data.length;++i,_8+=_7){
_6.createCylinder({center:{x:_8+_7/2,y:0,z:0},radius:_7/2-this.gap,height:this.data[i]*_9}).setTransform(dojox.gfx3d.matrix.rotateXg(-90)).setFill(this.material).setStroke(this.outline);
}
}});
})();
}
