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



<h1> kauftEin</h1>
<br>
<a href="http://wwwlab.cs.univie.ac.at/~a0947188/dbs_php/index.php">Zurueck zur Hauptseite</a><br>
<br>



<!-- >
kauftEin:
<-->

<div>
    <form id='searchform3' action='kauftein.php' method='get'>
        <a href='kauftein.php'>Alle kauftEin</a> ---
        Suche nach kauftEin (ProduktID):
        <input id='search3' name='search3' type='text' size='20' value='<?php if(isset($_GET['search3'])) echo $_GET['search3']; ?>' />
        <input id='submit3' type='submit' value='Los!' />
    </form>
</div>
<?php
// check if search view of list view
if (isset($_GET['search3'])) {
    $sql = "SELECT * FROM kauftEin WHERE ProduktID like '%" . $_GET['search3'] . "%'";
} else {
    $sql = "SELECT * FROM kauftEin";
}

// execute sql statement
$stmt = oci_parse($conn, $sql);
oci_execute($stmt);
?>

<table style='border: 1px solid #DDDDDD'>
    <thead>
    <tr>
        <th>URL</th>
        <th>UserID</th>
        <th>Pfad</th>
        <th>ProduktID</th>
        <th>Anzahl</th>
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
<div>Insgesamt <?php echo oci_num_rows($stmt); ?> Einkäufe gefunden!
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
