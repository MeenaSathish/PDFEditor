# PDFEditor
To compile the source code
gradle clean build

To run the application
gradle bootRunLocal


To test the application
1. Copy the file src/main/resources/MAPD-Data.csv to /tmp (or leave as is and change the script)
2. Open the Postman and execute edit-pdf.sql from ddm-scripts folder
3. Goto the src/main/resources/FillFormField.pdf and check whether all the data are populated correctly

Note: Do not change the header columns in the CSV file. This is used to identify the text and checkbox elements in the PDF
