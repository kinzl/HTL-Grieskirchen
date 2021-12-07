<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<?php
function verdopplung_quadrat ($wert)
{
    $doppelt = $wert*2;
    $quadrat = $wert*$wert;
    $ergebnis = array ('Verdopplung' => $doppelt, 'Quadrat' => $quadrat);
    return $ergebnis;
}
$rueckgabewert = verdopplung_quadrat (5);
$verdoppelter_wert = $rueckgabewert['Verdopplung'];
$wert_zum_quadrat = $rueckgabewert['Quadrat'];
print "Der doppelte Wert dieser Zahl beträgt: " .$verdoppelter_wert."<br>";

print "Das Quadrat dieser Zahl beträgt: " .$wert_zum_quadrat;

?>
</body>
</html>
