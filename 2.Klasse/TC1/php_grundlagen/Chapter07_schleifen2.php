<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<?php
$sortiment = array();
$produkt[0]['Produktname'] = "Bohrmaschine";
$produkt[0]['Preis'] = 45;
$produkt[0]['Anzahl'] = 6;
$produkt[1]['Produktname'] = "Kreissäge";
$produkt[1]['Preis'] = 79;
$produkt[1]['Anzahl'] = 0;
$produkt[2]['Produktname'] = "Bandschleifer";
$produkt[2]['Preis'] = 89;
$produkt[2]['Anzahl'] = 11;
$i = 0;
while ($i < 3)
{
    if ($produkt[$i]['Anzahl'] > 0)
    {
        print "<p>Produkt: " .$produkt[$i]['Produktname'].
            " Preis: ".$produkt[$i]['Preis']." Euro</p>\n";
    }
    $i++;
}
?>
</body>
</html>
