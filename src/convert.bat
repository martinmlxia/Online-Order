@echo off

del /S *.bak

native2ascii -encoding gbk other/messageCode_zhn.properties other/messageCode_zh.properties
native2ascii -encoding gbk orderMessages_zhn.properties orderMessages_zh.properties
native2ascii -encoding gbk validateMessages_zhn.properties validateMessages_zh.properties
native2ascii -encoding gbk lensModel_zhn.properties lensModel_zh.properties
native2ascii -encoding gbk importExcel_zhn.properties importExcel_zh.properties
native2ascii -encoding gbk viewOrder_zhn.properties viewOrder_zh.properties

pause
