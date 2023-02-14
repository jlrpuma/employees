# EmployeesBack

## Usage

1) Two endpoints weere provided on Controllers layer, to receive a CSV file (***Provide the file without headers, just with the information***) /
   1) *greaterOverlap* will get you the pair with the grater overlap in days
        <pre><code>curl --location --request POST 'localhost:8080/greaterOverlap' --form 'file=@"{PATH-TO-YOUR-CSV}"'</code></pre>
   2) *multipleOverlaps* will get you the full list of pairs that have worked in projects together with the amout of time overlaped in days
        <pre><code>curl --location --request POST 'localhost:8080/multipleOverlaps' --form 'file=@"{PATH-TO-YOUR-CSV}"'</code></pre>
2) Just provide a CSV with the right content to process the file

## Constraints

- This implementation takes as an assumption the file you are providing to the endpoint is in a compliant shape with the requiered stuff, validations
over it can be implemented.
- TimeUtil.parseDate can use all this formats to handle the data provided on csv "yyyy-MM-dd", "MM/dd/yyyy", "dd/MM/yyyy", "MM-dd-yyyy", "dd-MM-yyyy" 


## Tests

- Some test were added in order to check the bussines logic required to get the information processed they can be run with
      <pre><code>mvn test</code></pre>
