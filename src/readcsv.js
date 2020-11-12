const CSVToJSON = require("csvtojson")
const fs = require("fs")
const filepath = "C:\\Users\\y-tit\\OneDrive\\PROJECT3\\100g\\sample_submission.csv"
 
CSVToJSON().fromFile(filepath).then(source =>{  
  console.log(source)
  fs.writeFileSync('graph.json',JSON.stringify(source))
}); 
