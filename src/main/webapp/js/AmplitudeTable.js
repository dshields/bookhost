//example JSON
//artist = {
//             artistName: "name", 
//             albums:  
//             [ 
//               { 
//                 albumname: "albumName",
//                 artistname: "artistName",
//                 trackcount: "trackcount",
//                 year: "year",
//                 songs:
//                 [ 
//                   {
//                         id: "id",
//                         number: "track",
//                         name: "song name",
//                         artist:  "artistName",
//                         album: "albumname",
//                         length: "length",
//                         genre: "genre"
//                   }
//                 ]
//              }
//            ]};

AmplitudeTable = function()
{
   this.divId = ""; //div to place table in
   this.id = ""; // id to give the table widget
   this.title = ""; // title of table
   this.footerText = "";
   this.columns = [];
   this.rows = [];
   this.createWidget = createWidget;
   this.addStringColumn = addStringColumn;
   this.addNumberColumn = addNumberColumn;
   this.addDateColumn = addDateColumn;
   this.makeTable = makeTable;
   this.makeHeaders = makeHeaders;
   this.makeCells = makeCells;
   this.makeRows = makeRows;
   this.evenRowClass = "";
   this.oddRowClass = "";
   this.rowAttributes  = function(rowNumber,rowData) { return { };  };
   this.cellAttributes = function(colNumber,colData) { return { } ; };
   this.cleanup = cleanup;
   this.html = HTMLBuilder.apply(this);

   function cleanup()
   {
     delete this.rows;
     this.rows =[];
   }
   
   function stringCompare(value1,value2)
   {
        if(!value1 && value2) return -1;
        if(!value2 &&  value1) return 1;
        if(!value1 && !value2)  return 0;
        if (value1.toLowerCase()>value2.toLowerCase()) 
        {
            return 1; 
        } 
        else if (value1.toLowerCase()<value2.toLowerCase())
        { 
            return -1; 
        }
        return 0; 
   }

   function addNumberColumn(columnName,columnLabel,attributes)
   {
        attributes = attributes || { };
        var newColumn = 
            { field:columnName, label:columnLabel, dataType:"Number", format:"####", noSort:true, cls:"numberClass",attrs:attributes };
        this.columns.push(newColumn);
        return newColumn;
   }
    
   function addStringColumn(columnName,columnLabel)
   {
         var newColumn = 
            { field:columnName, label:columnLabel, dataType:"String", format:"textClass", noSort:true, sortFunction:stringCompare,
              filtCaseIns:true, align:"center", valign:"bottom", cls:"textClass" };
        this.columns.push(newColumn);
        return newColumn;    
   }
    
   function addDateColumn(columnName,columnLabel)
   {
      var newColumn =  
          {  field:columnName, label:columnLabel, dataType:"Date", format:"%Y/%m/%d", noSort:true, cls:"dateClass" }
      this.columns.push(newColumn);
      return newColumn;
   }
    
   function makeHeaders()
   {
        var headers = [];
        for(var i in this.columns)
        {
        headers.push(this.TH({"class":"th"+i}, this.columns[i].label ));
        }
        return headers;
   }
    
   function makeCells(row)
   {
      var cells = [];
      for(var i in this.columns)
      {
        var field = this.columns[i].field;
        cells.push(this.TD({"class":"td"+i}, row[field]));
      }   
      return cells;  
   }
    
   function makeRows()
   {
      var elems = [];
      for(var i in this.rows) 
      {
          var row = this.rows[i];
          var attrs = this.rowAttributes(i,row);
          var cells = this.makeCells.call(this,row);
          var tableRow = this.TR(attrs,cells);
          elems.push(tableRow);
      }
      return elems;
   }
   
   function makeTable()
   {
        var colCount = this.columns.length;
        table = 
        this.TABLE({id:this.id,cellspacing:0,"class":"sTable"},
            this.THEAD( 
               this.TR({"class":"sTH"},makeHeaders.call(this))
            ),
            this.TFOOT(
               this.TR({},this.TD({colspan:colCount,"class":"sTFoot"},this.footerText))
            ),
            this.TBODY({"class":"sTBody"},
                this.TR({},this.TD({colspan:colCount},
                    this.DIV({"class":"sInnerDiv"},
                        this.TABLE({id:this.id+"Inner",cellspacing:0,"class":"sInnerTable"},
                            this.TBODY({"class":"sInnerTBody"},makeRows.call(this)))))))
        );
        return table;

   }

   function createWidget() 
   {
        var containerDiv = dojo.byId(this.divId);
        containerDiv.innerHTML = this.makeTable();
   }
}
