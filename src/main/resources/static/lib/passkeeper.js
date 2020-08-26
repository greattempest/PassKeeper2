function cardno(no){
    var cno=no+"";
    var length=cno.length;
    if(length<=4) 
        cno=cno.substring(0,1)+'***';
    else if(length<=8)
        cno=cno.substring(0,2)+'****'+cno.substring(length-2);
    else
        cno=cno.substring(0,4)+'****'+cno.substring(length-4);
        document.write(cno);
}
