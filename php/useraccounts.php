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



<h1> Useraccounts</h1>
<br>
<a href="http://wwwlab.cs.univie.ac.at/~a0947188/dbs_php/index.php">Zurueck zur Hauptseite</a><br>
<br>



<!-- >
Useraccounts:
<-->

<div>
    <form id='searchform1' action='useraccounts.php' method='get'>
        <a href='useraccounts.php'>Alle UserAccounts</a> ---
        Suche nach UserAccounts (UserID):
        <input id='search1' name='search1' type='text' size='20' value='<?php if(isset($_GET['search1'])) echo $_GET['search1']; ?>' />
        <input id='submit1' type='submit' value='Los!' />
    </form>
</div>
<?php
// check if search view of list view
if (isset($_GET['search1'])) {
    $sql = "SELECT * FROM UserAccount WHERE UserID like '%" . $_GET['search1'] . "%'";
} else {
    $sql = "SELECT * FROM UserAccount";
}

// execute sql statement
$stmt = oci_parse($conn, $sql);
oci_execute($stmt);
?>

<table style='border: 1px solid #DDDDDD'>
    <thead>
    <tr>
        <th>Email</th>
        <th>UserID</th>
        <th>Bank</th>
        <th>Kontonummer</th>
        <th>Adresse</th>
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
<div>Insgesamt <?php echo oci_num_rows($stmt); ?> Useraccounts gefunden!
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
