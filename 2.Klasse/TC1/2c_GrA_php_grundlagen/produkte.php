<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Produkte</title>
    </head>
    <body>
        <h1>Unsere Produkte</h1>
        <form action="produkte.php" method="get">
            <p>
                <input type="text" name="search">
                <button type="submit">Suchen</button>
            </p>
        </form>
        <?php
            try
            {
                $dbh = new PDO("mysql:dbname=onlineshopDB;host=localhost", "root", "");
                print "DB-Verbindung erfolgreich hergestellt.";

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

        ?>
    </body>
</html>
