<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<?php
include ("Chapter09_Klassen.php");

$meinAuto = new Auto();

$meinAuto->geschwindigkeit = 150;
$meinAuto->kraftstroffverbrauch = 7;

print "Geschwindigkeit: ".$meinAuto->geschwindigkeit." km/h<br>\n";
print "Kraftstoffverbrauch: ".$meinAuto->kraftstroffverbrauch."1/100km<br>\n";
$meinAuto->setSitzplaetze(2);

?>
</body>
</html>
