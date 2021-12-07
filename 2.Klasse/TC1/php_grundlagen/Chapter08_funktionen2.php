<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<?php
function begruessung ($text, $ansprache)
{
    print $text." ".$ansprache."!";
}
$gruss = "Guten Morgen";
$leser = "Herr Müller";
begruessung ($gruss, $leser);
?>
</body>
</html>
