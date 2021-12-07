<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<?php
$anzahl = 50;
$bedingung = true;
print "while schleife\n";
while ($bedingung)
{
    print "<p>Aktuelle Anzahl:".$anzahl."</p>\n";
    $anzahl--;
    $bedingung = ($anzahl > 20);
}
?>

<?php
$anzahl = 50;
$bedingung = true;

print "do while schleife\n";

do
{
    print "<p>Aktuelle Anzahl:".$anzahl."</p>\n";
    $anzahl--;
    $bedingung = ($anzahl > 20);
}
while ($bedingung);
?>
</body>
</html>
