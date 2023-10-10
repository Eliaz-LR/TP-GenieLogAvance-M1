<html>
<head>
  <title>Invoice</title>
</head>
<body>
  <h1>Invoice</h1>
  <ul>
    <li><b>Client :</b> ${invoice.customer}</li>
  </ul>
  <table>
    <thead>
        <tr>
            <th colspan="4"> Invoice Table </th>
        </tr>
        <tr>
            <td class = "categorie">Piece</td>
            <td class = "categorie">Seats sold</td>
            <td class = "categorie">Price</td>
        </tr>
    </thead>
    <tbody>
        <#list invoice.performances as performance>
        <tr>
            <td>${performance.play.name}</td>
            <td>${performance.audience}</td>
            <td>$650.00</td>
        </tr>
        </#list>
        <tr>
            <td class="endTab", colspan="2">Total owed:</td>
            <td >$1,730.00</td>
        </tr>
        <tr>
            <td class="endTab", colspan="2">Fidelity points earned: </td>
            <td >47</td>
        </tr>
    </tbody>
  </table>
  <p id="endMsg">Payment is required under 30 days.</p>
</body>
</html>
