/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function checkAndSubmit()
{
    var moisLiv = document.getElementById('IDLIV').value;
    if (document.getElementById(moisLiv).selectedIndex > 0)

    {
        var idLivraison = document.getElementById('ID').value;
        document.getElementById(idLivraison).submit();
    }
}



