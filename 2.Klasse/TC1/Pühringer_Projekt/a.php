<?php
function getWebsite(){
    try
    {
        $dbh = new PDO();
        //print "DB-Verbindung erfolgreich hergestellt.";

        $sql = "SELECT * FROM produkt WHERE p_name LIKE '%" . $_REQUEST["search"] . "%' OR p_beschreibung LIKE '%" . $_REQUEST["search"] . "%'";

        $result = $dbh->query($sql);

        foreach ($result as $p)
        {
            print "<h3>" . $p["p_name"] . "</h3>";
            print "<p>Preis: " . $p["p_preis"]. "</p>";
            print "<p>" . $p["p_beschreibung"]. "</p>";
            print "<p>Lagerstand: " . $p["p_anzahl"]. " Stück</p>";
        }

    }
    catch (PDOException $e)
    {
        print $e->getMessage();
    }
}
?>