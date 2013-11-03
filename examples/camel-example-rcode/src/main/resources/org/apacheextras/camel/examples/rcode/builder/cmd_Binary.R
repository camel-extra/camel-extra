dev.off();
r=readBin('${exchangeId}.jpg','raw',1024*1024);
unlink('${exchangeId}.jpg');
r;