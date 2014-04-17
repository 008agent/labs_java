<%@page import="org.apache.jasper.JasperException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.Area"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ratnikov V.I. 4105 var 576</title>
    </head>
    
    <script type="text/javascript" src="scripts.js"></script>
    <link rel='stylesheet' href="stylesheet.css">
    
    <body>
        <h1>Click on image or select input values and click 'submit'</h1>
        <div class="image">
            <img src="area.png" id="img_main" onclick="img_click(event,this)">
        </div>
        
        <form method="POST" id="form_main">
            <div class="inputX">
                <input type="hidden" id="X" name="X" value="-2">
                <h3>Choose X:</h3>
                <table border="1">
                    <tr>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="-2" checked>-2</td>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="-1.5">-1.5</td>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="-1">-1</td>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="-0.5">-0.5</td>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="0">0</td>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="0.5">0.5</td>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="1">1</td>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="1.5">1.5</td>
                        <td><input type="radio" onclick="setX(this)" name="X1" value="2">2</td>
                    </tr>
                </table>
            </div>
            
            <div class="inputY">
                <h3>Enter Y:</h3>
                <input type="text" id="Y" name="Y">
            </div>
            
            <div class="inputR">
                <h3>Click to set R:</h3>
                <input type="hidden" id="R" name="R">
                <table border="1">
                    <tr>
                        <td><input type="button" onclick="setR(this)" value="1"></td>
                        <td><input type="button" onclick="setR(this)" value="1.5"></td>
                        <td><input type="button" onclick="setR(this)" value="2"></td>
                        <td><input type="button" onclick="setR(this)" value="2.5"></td>
                        <td><input type="button" onclick="setR(this)" value="3"></td>
                    </tr>
                </table>
            </div>
            
            <div class="inputSubmit">
                <h3>Click to send data:</h3>
                <input type="button" value="check" onclick="submit_form()">
            </div>
        </form>
        
        
        <div class="results">
            <table border="1">
                <%=processRequest(request,response)%>
            </table>
        </div>

    </body>
</html>


<%!
String processRequest(HttpServletRequest request,HttpServletResponse response){
    String tmp = "";
    HttpSession session = request.getSession();
    String old = (String)session.getAttribute("context");
        String X,Y,R;
        X = request.getParameter("X");
        Y = request.getParameter("Y");
        R = request.getParameter("R");
        if(X==null || Y==null || R==null)
        {
            tmp = "<tr><td>X</td><td>Y</td><td>R</td><td>Result</td></tr>"; 
            old = "";
        }
        else
        {
            Area a = new Area(Double.parseDouble(R));
            try
            {

                 tmp = "<tr><td>" + X + "</td><td>" + Y + "</td><td>" + R + "</td><td>"+ a.Hit(Double.parseDouble(X),Double.parseDouble(Y))+"</td></tr>";
            }
            catch(Exception E)
            {
                
            }
        }
    old += tmp;
    session.setAttribute("context", old);
    return old;
}
%>