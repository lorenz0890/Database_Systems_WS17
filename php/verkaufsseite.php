<?php
$user = 'a00947188';
$pass = 'lorenzk220890';
$database = 'lab';

// establish database connection
$conn = oci_connect($user, $pass, $database);
if (!$conn) exit;
?>

<html>
<head>
</head>
<body>



<h1> Verkaufsseite</h1>
<br>
<a href="http://wwwlab.cs.univie.ac.at/~a0947188/dbs_php/index.php">Zurueck zur Hauptseite</a><br>
<br>


<!-- >
Produkt:
<-->

<div>
    <form id='searchform2' action='verkaufsseite.php' method='get'>
        <a href='verkaufsseite.php'>Alle Verkaufsseite</a> ---
        Suche nach Verkaufsseite (URL):
        <input id='search2' name='search2' type='text' size='20' value='<?php if(isset($_GET['search2'])) echo $_GET['search2']; ?>' />
        <input id='submit2' type='submit' value='Los!' />
    </form>
</div>
<?php
// check if search view of list view
if (isset($_GET['search2'])) {
    $sql = "SELECT * FROM Verkaufsseite WHERE URL like '%" . $_GET['search2'] . "%'";
} else {
    $sql = "SELECT * FROM Verkaufsseite";
}

// execute sql statement
$stmt = oci_parse($conn, $sql);
oci_execute($stmt);
?>

<table style='border: 1px solid #DDDDDD'>
    <thead>
    <tr>
        <th>URL</th>
        <th>Pfad</th>
        <th>Titel</th>
    </tr>
    </thead>
    <tbody>
    <?php
    // fetch rows of the executed sql query
    while ($row = oci_fetch_assoc($stmt)) {
        echo "<tr>";
        //var_dump($row[SteuernummerUID]); break;
        foreach ($row as $item)
            echo "<td>" . ($item !== null ? htmlentities($item, ENT_QUOTES) : "&nbsp;") . "</td>\n";
        echo "</tr>";
    }
    ?>
    </tbody>
</table>
<div>Insgesamt <?php echo oci_num_rows($stmt); ?> Verkaufsseiten gefunden!
    <br>
    <br>
    <br>
</div>
<?php  oci_free_statement($stmt); ?>


<?php
oci_close($conn);
?>
</body>
</html>
