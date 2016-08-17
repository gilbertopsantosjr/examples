<?php
$conecta = mysql_connect("localhost", "root", "root") or print (mysql_error()); 
print "Conexão OK!"; 
mysql_close($conecta); 
?>