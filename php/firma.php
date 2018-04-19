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



<h1> Firmen</h1>
<br>
<a href="http://wwwlab.cs.univie.ac.at/~a0947188/dbs_php/index.php">Zurueck zur Hauptseite</a><br>
<br>


<!-- >
Firmenliste + insert + stored procedure:
<-->

<div>
    <form id='searchform' action='firma.php' method='get'>
        <a href='firma.php'>Alle Firmen</a> ---
        Suche nach Firmenname:
        <input id='search' name='search' type='text' size='20' value='<?php if(isset($_GET['search'])) echo $_GET['search']; ?>' />
        <input id='submit' type='submit' value='Los!' />
    </form>
</div>
<?php
// check if search view of list view
if (isset($_GET['search'])) {
    $sql = "SELECT * FROM Firma WHERE Name like '%" . $_GET['search'] . "%'";
} else {
    $sql = "SELECT * FROM Firma";
}

// execute sql statement
$stmt = oci_parse($conn, $sql);
oci_execute($stmt);
?>

<table style='border: 1px solid #DDDDDD'>
    <thead>
    <tr>
        <th>SteuernummerUID</th>
        <th>Name</th>
        <th>Firmenbuchnummer</th>
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
        //echo "<td>" . $row["SteuernummerUID"] . "</td>";
        //echo "<td>" . $row['Name'] . "</td>";
        //echo "<td>" . $row['Firmenbuchnummer'] . "</td>";
        //echo "<td>geb. am " . $row['GEBDATUM'] . " in " . $row['GEBORT'] . "</td>";
        echo "</tr>";
    }
    ?>
    </tbody>
</table>
<div>Insgesamt <?php echo oci_num_rows($stmt); ?> Firmen gefunden!</div>
<?php  oci_free_statement($stmt); ?>

<div>
    <form id='insertform' action='firma.php' method='get'>
        Neue Firma einfuegen:
        <table style='border: 1px solid #DDDDDD'>
            <thead>
            <tr>
                <th>steuernummerid</th>
                <th>name</th>
                <th>firmenbuchnummer<th>
                    <!--th>firmenbuchnummer</th>
                    <th>plz</th>
                    <th>strasse</th>
                    <th>gebdatum (yyyy-mm-dd)</th>
                    <th>gebort</th-->
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <input id='steuernummerid' name='steuernummerid' type='text' size='10' value='<?php if(isset($_GET['steuernummerid'])) echo $_GET['steuernummerid']; ?>' />
                </td>
                <td>
                    <input id='name' name='name' type='text' size='20' value='<?php if(isset($_GET['name'])) echo $_GET['name']; ?>' />
                </td>
                <td>
                    <input id='firmenbuchnummer' name='firmenbuchnummer' type='text' size='20' value='<?php if(isset($_GET['firmenbuchnummer'])) echo $_GET['firmenbuchnummer']; ?>' />
                </td>
            </tr>
            </tbody>
        </table>
        <input id='submit' type='submit' value='Insert!' />
    </form>
</div>


<?php
//Handle insert
if (isset($_GET['steuernummerid']))
{
    //var_dump($_GET['steuernummerid']);
    //Prepare insert statementd
    $sql = "INSERT INTO Firma VALUES('" . ($_GET['steuernummerid']) . "','"  . $_GET['name'] . "','" . $_GET['firmenbuchnummer'] .  "')";
    //Parse and execute statement
    $insert = oci_parse($conn, $sql);
    oci_execute($insert);
    $conn_err=oci_error($conn);
    $insert_err=oci_error($insert);
    if(!$conn_err & !$insert_err){
        print("Successfully inserted");
        print("<br>");
    }
    //Print potential errors and warnings
    else{
        print($conn_err);
        print_r($insert_err);
        print("<br>");
    }
    oci_free_statement($insert);
}
?>

<div>
    <form id='searchabt' action='firma.php' method='get'>
        Suche URL zu bestimmter Firma (SteuernummerUID):
        <input id='steuernummerid' name='steuernummerid' type='text' size='20' value='<?php if(isset($_GET['steuernummerid'])) echo $_GET['steuernummerid']; ?>' /> <!-- man muss den value raus nehmen, damit nichts mehr in den feldern drinnen steht. -->
        <input id='submit1' type='submit' value='Aufruf Stored Procedure!' />
    </form>
</div>

<?php
//Handle Stored Procedure
if (isset($_GET['steuernummerid']))
{
    //Call Stored Procedure
    $steuernummerid = $_GET['steuernummerid'];
    $url='';
    $sproc = oci_parse($conn, 'begin firma_temp(:p1, :p2); end;');
    //Bind variables, p1=input (nachname), p2=output (abtnr)
    oci_bind_by_name($sproc, ':p1', $steuernummerid);
    oci_bind_by_name($sproc, ':p2', $url, 64);
    oci_execute($sproc);
    $conn_err=oci_error($conn);
    $proc_err=oci_error($sproc);

    if(!$conn_err && !$proc_err){
        echo("<br><b>" . $steuernummerid . " hat die URL . " . $url . "</b><br>" );  // prints OUT parameter of stored procedure
    }
    else{
        //Print potential errors and warnings
        print($conn_err);
        print_r($proc_err);
    }
}

// clean up connections
oci_free_statement($sproc);
oci_close($conn);
?>
</body>
    </html>