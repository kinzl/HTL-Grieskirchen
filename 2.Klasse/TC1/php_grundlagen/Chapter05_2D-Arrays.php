<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

<?php
$sortiment = array();
$sortiment[0]['Artikelnummer'] = 1;
$sortiment[0]['Produktname'] = "Bohrmaschine";
$sortiment[0]['Preis'] = 45;
$sortiment[1]['Artikelnummer'] = 2;
$sortiment[1]['Produktname'] = "Kreissäge";
$sortiment[1]['Preis'] = 79;
$sortiment[2]['Artikelnummer'] = 3;
$sortiment[2]['Produktname'] = "Bandschleifer";
$sortiment[2]['Preis'] = 89;
print $sortiment[0]['Produktname']."<br>\n";
print $sortiment[1]['Produktname']."<br>\n";
print $sortiment[2]['Produktname']."<br>\n";
print $sortiment[0]['Preis']."<br>\n";
print $sortiment[1]['Preis']."<br>\n";
print $sortiment[2]['Preis']."<br>\n";
?>


</body>
</html>
