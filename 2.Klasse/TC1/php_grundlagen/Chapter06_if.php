<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

<?php
$var1 = 3;
$var2 = 5;
//&& wird durch and ersetzt, funktioniert auch bei or
if ($var1 < 5 and $var2 == 7)
{
    print "Variable 1 ist kleiner als 5 und Variable 2 beträgt 7.";
    print "Variable 1".$var1." ,Variable 2".$var2;
} else if ($var1 < 5 && $var2 == 7)
{
    print "Variable 1 ist kleiner als 5 und Variable 2 beträgt 7.";
    print "Variable 1".$var1." ,Variable 2".$var2;
}
?>

</body>
</html>
