<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

<?php
$produkt = array();
$produkt['Artikelnummer'] = 1;
$produkt['Produktname'] = "Bohrmaschine";
$produkt['Preis'] = 45;
$produkt['Beschreibung'] = "Kraftvolle Bohrmaschine für Bohr- und 
Schraubarbeiten";
$produkt['Anzahl'] = 23;

$produkt = array('Artikelnummer' => 1, 'Produktname' =>
    "Bohrmaschine", 'Preis' => 45, 'Beschreibung' => "Kraftvolle 
Bohrmaschine für Bohr- und Schraubarbeiten", 'Anzahl' => 23);

print $produkt['Artikelnummer']."<br>\n";
print $produkt['Produktname']."<br>\n";
print $produkt['Preis']."<br>\n";
print $produkt['Beschreibung']."<br>\n";
print $produkt['Anzahl']."<br>\n";

?>


</body>
</html>
