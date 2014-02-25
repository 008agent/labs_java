/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author s153335
 */
@WebServlet(urlPatterns = {"/servlet"})
public class servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        boolean result = false;
        String paramX = request.getParameter("X");
        String paramY = request.getParameter("Y");
        String paramR = request.getParameter("R");
        
        double X = Double.parseDouble(paramX);
        double Y = Double.parseDouble(paramY);
        double R = Double.parseDouble(paramR);
        double tmpX = Math.abs(X);
        double tmpY = Math.abs(Y);
        
        
        //first quarter
        if( X>0 && Y>0 )
        {
            result = false;
        }
        //second quarter
        else if( X<=0 && Y>=0 )
        {
            if( tmpY <= R/2 - tmpX )
            {
               result = true;
            }
        }
        //thied quarter
        else if( X<=0 && Y<=0 && R>=tmpX )
        {
            if( Math.sqrt( R*R - tmpX*tmpX ) >= tmpY )
            {
                result = true;
            }    
        }
        //fourth quarter
        else if( X>=0 && Y<=0 && tmpX<=R/2 && tmpY<=R )
        {
          result = true;
        }
        
        try 
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ratnikov V.I. 4105. var 575</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet main at " + request.getContextPath() + "</h1>");
                out.print("<p>X: " + paramX + "</p>");
                out.print("<p>Y: " + paramY + "</p>");
                out.print("<p>R: " + paramR + "</p>");
                out.print("<p>Target hit: " + String.valueOf(result) +  "</p>");
            out.println("</body>");
            out.println("</html>");
        } finally 
        {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
