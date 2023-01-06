# Othergame
Proyecto desarrollado en la asignatura de Computación Web, UC3M

Consiste en una herramieta web para el intercambio de juegos de mesa.
Los usuarios tienen su propio perfil en el cual comparten los juegos que disponen y están dispuestos a cambiar por otro.
El funcionamiento sigue así:
 - Usuario A está interesado en el juego X del usuario B y lo solicita
 - Usuario B recibe la notificación de interés de usuario A y comprueba los juegos que este tiene. Si le interesa uno lo solicita, si no se termina el trato.
 - Usuario A comprueba la oferta final y si llegan a un acuerdo se intercammbian los contactos.

Los siguientes apuntes son acerca del proyecto:

<h2><span style="font-weight: 400;">Modelo de datos</span></h2>
<ol>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Usuarios</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Anuncio</span>
<ol>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Estados:</span>
<ol>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Publicado</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Retirado</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Completado</span></li>
</ol>
</li>
</ol>
</li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Propuesta de intercambio</span>
<ol>
<li><span style="font-weight: 400;">Estados:</span>
<ol>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Propuesta</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Aceptada</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Confirmada</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Rechazada</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">No disponible</span></li>
</ol>
</li>
</ol>
</li>
</ol>
<p><span style="font-weight: 400;">Hay una clase de tipo bean para cada entidad de la base de datos (Usuario, Anuncio, PropuestaIntercambio).</span></p>
<h2><span style="font-weight: 400;">Vistas de la aplicaci&oacute;n</span></h2>
<ol>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Vista principal</span>
<ol>
<li><span style="font-weight: 400;">Controlador (servlet):</span>&nbsp;
<ol>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Comprobar que el usuario tenga sesi&oacute;n activa</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Recuperar de BD los objetos a mostrar en la vista (anuncios, procesos de intercambio, etc.)</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Guardar cada objeto como atributo de request</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Forward a la plantilla JSP</span></li>
</ol>
</li>
<li><span style="font-weight: 400;">Plantilla JSP:</span>
<ol>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Recuperar cada objeto del atributo request</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Mostrar la informaci&oacute;n, formularios, etc.</span></li>
</ol>
</li>
</ol>
</li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Vista de resultados de b&uacute;squeda</span>
<ol>
<li><span style="font-weight: 400;">Controlador:</span>
<ol>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Comprobar que el usuario tenga sesi&oacute;n activa</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Recuperar las palabras a buscar con request.getParameter()</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Obtener los resultados de la base de datos (objetos de la clase Anuncio)</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Guardar la lista de anuncios como atributo de request</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Forward a la plantilla JSP</span></li>
</ol>
</li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Plantilla JSP</span></li>
</ol>
</li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Vista de perfil de usuario</span>
<ol>
<li><span style="font-weight: 400;">Controlador:</span>
<ol>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Comprobar que el usuario tenga sesi&oacute;n activa</span></li>
<li style="font-weight: 400;" aria-level="3"><strong>Recuperar el id del usuario</strong><span style="font-weight: 400;"> a mostrar con request.getParameter()</span>
<ul>
<li style="font-weight: 400;" aria-level="4"><span style="font-weight: 400;">Por ejemplo, se puede llegar a esta vista mediante hiperv&iacute;nculos del tipo &lt;a href=&rdquo;usuario?id=128&rdquo;&gt;Nemo&lt;/a&gt;.</span></li>
</ul>
</li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Recuperar a ese usuario de la base de datos</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Recuperar otros datos relevantes del usuario a mostrar (anuncios, etc.)</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Guardar cada objeto como atributo de request</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Forward a la plantilla JSP</span></li>
</ol>
</li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Plantilla JSP</span></li>
</ol>
</li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Vista de anuncio</span>
<ol>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Controlador</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Plantilla JSP</span></li>
</ol>
</li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Vista de intercambio</span>
<ol>
<li><span style="font-weight: 400;">Controlador:</span>
<ol>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Comprobar que el usuario tenga sesi&oacute;n activa</span></li>
<li style="font-weight: 400;" aria-level="3"><span style="font-weight: 400;">Comprobar si el usuario est&aacute; autorizado (participa en el intercambio)</span></li>
</ol>
</li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Plantilla JSP</span></li>
</ol>
</li>
</ol>
<p>&nbsp;</p>
<h2><span style="font-weight: 400;">Acciones</span></h2>
<p><span style="font-weight: 400;">Cada acci&oacute;n es un controlador (servlet). Cada controlador hace una operaci&oacute;n peque&ntilde;a, acotada.</span></p>
<p><span style="font-weight: 400;">Se llegar con mensajes POST (formularios con method=&rdquo;post&rdquo;).</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">Ejemplo de formulario (retirar un anuncio):</span></p>
<p>&nbsp;</p>
<p><em><span style="font-weight: 400;">Monopoly</span></em></p>
<p><em><span style="font-weight: 400;">Condici&oacute;n: como nuevo</span></em></p>
<p><em><span style="font-weight: 400;">Usuario: Nemo</span></em></p>
<p><em><span style="font-weight: 400;">Retirar</span></em></p>
<p>&nbsp;</p>
<p><em><span style="font-weight: 400;">&lt;form action=&rdquo;retirar&rdquo; method=&rdquo;post&rdquo;&gt;</span></em></p>
<p><em><span style="font-weight: 400;">&nbsp;&nbsp;&nbsp;&nbsp;&lt;input type=&rdquo;submit&rdquo; value=&rdquo;Retirar&rdquo;&gt;</span></em></p>
<p><em><span style="font-weight: 400;">&nbsp;&nbsp;&nbsp;&nbsp;&lt;input type=&rdquo;hidden&rdquo; name=&rdquo;anuncio&rdquo; value=&rdquo;678&rdquo;&gt;</span></em></p>
<p><em><span style="font-weight: 400;">&lt;/form&gt;</span></em></p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">Lista de acciones:</span></p>
<ul>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Iniciar sesi&oacute;n</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Cerrar sesi&oacute;n</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Publicar un anuncio:</span></li>
<ul>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Comprobar que el usuario tenga sesi&oacute;n abierta</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Recuperar con request.getParameter() los datos del anuncio (t&iacute;tulo, descripci&oacute;n, etc.)</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Insertar el anuncio en base de datos (y averiguar el id que se le ha asignado).</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">response.sendRedirect(&ldquo;anuncio?id=678&rdquo;) a la vista de ese anuncio que se acaba de crear.</span></li>
</ul>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Retirar un anuncio:</span></li>
<ul>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Comprobar que el usuario tenga sesi&oacute;n abierta</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Recuperar con request.getParameter() el id del anuncio a retirar</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Obtener el objeto anuncio de la base de datos</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Comprobar autorizaci&oacute;n: el usuario con sesi&oacute;n abierta debe ser el propietario del anuncio.</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Comprobar estado del anuncio: el anuncio tiene que estar en estado publicado.</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Cancelar todas las propuestas de intercambio en curso para este anuncio</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">response.sendRedirect(&ldquo;anuncio?id=678&rdquo;) a la vista de ese anuncio que se acaba de crear.</span></li>
</ul>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Iniciar una propuesta de intercambio</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Aceptar una propuesta de intercambio</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Confirmar una propuesta de intercambio</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Rechazar una propuesta de intercambio</span></li>
</ul>
<p>&nbsp;</p>
<h2><span style="font-weight: 400;">Autenticaci&oacute;n de usuarios</span></h2>
<ul>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">P&aacute;gina de autenticaci&oacute;n (llamada index.html). Formulario de autenticaci&oacute;n. Destino del formulario es el controlador de iniciar sesi&oacute;n.</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Formulario de iniciar sesi&oacute;n:</span></li>
<ul>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Coger email y contrase&ntilde;a con request.getParameter()</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Buscar en base de datos al usuario con dicho email y contrase&ntilde;a</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Si lo hay, guarda el objeto usuario en sesi&oacute;n (session.setAttribute(&ldquo;usuario&rdquo;, usuario) y response.sendRedirect(&ldquo;principal&rdquo;).</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">Si no lo hay, va a p&aacute;gina de error de autenticaci&oacute;n con response.sendRedirect(&ldquo;error-autenticacion.html&rdquo;).</span></li>
</ul>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Cualquier controlador comprueba si hay sesi&oacute;n abierta mirando si el usuario est&aacute; guardado en sesi&oacute;n (session.getAttribute(&ldquo;usuario&rdquo;)). Si devuelve null, no hay sesi&oacute;n iniciada (response.sendRedirect(&ldquo;index.html&rdquo;))</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Cerrar sesi&oacute;n:</span></li>
<ul>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">session.invalidate()</span></li>
<li style="font-weight: 400;" aria-level="2"><span style="font-weight: 400;">response.sendRedirect(&ldquo;index.html&rdquo;)</span></li>
</ul>
</ul>
<p>&nbsp;</p>
<h2><span style="font-weight: 400;">Adicional</span></h2>
<ol>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">C&oacute;mo buscar por palabras clave en MySQL.</span></li>
<li style="font-weight: 400;" aria-level="1"><span style="font-weight: 400;">Seguridad en las b&uacute;squedas y autenticaci&oacute;n.</span></li>
</ol>
<p>&nbsp;</p>
