<?php

include_once("common_functions.php");
session_start();
ob_start();

if( !isset($_SESSION['user']) || !isset($_SESSION['pass']) || !isset($_SESSION['id']) )
{
    header("Location: login.php");
    exit;
}

foreach ($_GET as $key=>$value) {
   echo "GET KEY: $key GET VALUE: $value\n";
}

foreach ($_POST as $key=> $value) {
   echo "POST KEY: $key POST VALUE: $value\n";
}

function submitSurvey($user_id)
{
    connect_db();
    ?>
    <div class="ptright">
    <?php
	if( !isset($_POST['action']) )
	{
	?>
	          
<?php

	//Grabs all relevent data used to load the widget.
	$categoryList = array();
	$categoryList;
	$outerArray;
	$innerArray = array();
	$fields;
	$query = "select * from User_Survey where userid = $user_id";
    $result = mysql_query($query) or die(mysql_error());
	while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
		$optionNumber = 7;
		$surveyid = $row[0];
		$categoryid = $row[2];
		$userid = $row[1];
		$fields = $row[18];
		$categoryType = $row[6];
		$categoryName = $row[3];
	
		//Load the options into an array
		while($fields > 0){
				array_push($innerArray, $row[$optionNumber]);
				$optionNumber = $optionNumber+1;
				$fields = $fields - 1;
		}
	
		//Make the JSON array
		$outerArray = array("surveyID"=>$surveyid, "categoryID"=>$categoryid, "userID"=>$userid, "fields"=>$fields,
		"categoryType"=>$categoryType, "categoryName"=>$categoryName, "options"=>$innerArray);
		
		//var_dump($outerArray);
		$jsonString = json_encode($outerArray, JSON_FORCE_OBJECT);
		
		array_push($categoryList, $jsonString);
		
	}
	
	//Add up all the JSON Categories into a JSON Array
	$jsonResult = '['.implode(",", $categoryList).']';
	
	//Display the JSON data
	printf($jsonResult);
	
	mysql_free_result($result);
}
}

?>