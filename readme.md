BillMortem is a utility which helps the user to do a postmortem on credit card bill. Once the user opens the bill, pdf reader view will display all the transaction and group all the distinct transaction on a pie chart. The user can search either by search bar or by highlighting a word/multiple words as a result user will be presented with sectioned transaction and respective total.
Currently, this is supported only for HDFC and CITI credit card bill.

Caching of parsed transaction is supported for fast retrieval of the transaction. The searching algorithm has been updated to KMP Algorithm. 

Displays previously selected pdf and let user to open the new one. Caching is implemented if user want then the pdf can be loaded from cache also.
<img src="https://github.com/pradeepxpankaj/BillMortem/blob/master/src/main/res/1.png" alt="pdf chooser" width="320px" height="240px">

Password dialog is prompted for password protedted file.
<img src="https://github.com/pradeepxpankaj/BillMortem/blob/master/src/main/res/2.png" alt="pdf chooser" width="320px" height="240px">

Pdf reader view - Displays all the transactions in the selected pdf in the left pane and distinct transactions with expenditure in the form of Pie chart on the right. The user can search by selecting terms in the transactions which are displayed in the left section or writing words on the search bar. The user can also view the selected pdf content as a raw format. The user can clean cache and enable/disable loading of new pdf file from cache.
<img src="https://github.com/pradeepxpankaj/BillMortem/blob/master/src/main/res/3.png" alt="pdf chooser" width="600px" height="400px">

The user is presented with all the total expenditure for the common transactions which may have happened at the same place(shop, marts etc) but the description text for those transactions are not same. Which can be achived by clicking <img src="https://github.com/pradeepxpankaj/BillMortem/blob/master/src/main/res/img_analytics.png" alt="pdf chooser" width="24px" height="24px">
<img src="https://github.com/pradeepxpankaj/BillMortem/blob/master/src/main/res/4.png" alt="pdf chooser" width="600px" height="400px">

The user can search by date, word, and amount.
<img src="https://github.com/pradeepxpankaj/BillMortem/blob/master/src/main/res/5.png" alt="pdf chooser" width="600px" height="400px">
Features:

    Analytics

    Single word search

    Multiple-word search

    Search by date

    Search by amount

    Search term selection by mouse

    Full PDF viewer
