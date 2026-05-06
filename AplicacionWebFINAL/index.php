<?php

session_start();
$cadena_conexion = 'mysql:dbname=aplicacion_web;host=127.0.0.1';
$usuario_bd = 'root';
$clave_bd = '';

$sesion_activa = isset($_SESSION["usuario"]);
$nombre_usuario = $sesion_activa ? $_SESSION["usuario"] : '';
$suscrito = false;

$noticias = [];
try {
    $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);

    if ($sesion_activa) {
        // Comprobamos si el usuario está suscrito
        $stmt_sub = $bd->prepare("SELECT subscrito FROM usuarios WHERE id_usuario = ?");
        $stmt_sub->execute([$_SESSION["id"]]);
        $fila = $stmt_sub->fetch(PDO::FETCH_ASSOC);
        $suscrito = $fila && $fila['subscrito'];

        // Usuario logueado: todas las noticias
        $stmt = $bd->query("SELECT * FROM noticias ORDER BY creado_en DESC");
    } else {
        // Sin sesión: solo las 2 primeras noticias
        $stmt = $bd->query("SELECT * FROM noticias ORDER BY creado_en DESC LIMIT 2");
    }

    $noticias = $stmt->fetchAll(PDO::FETCH_ASSOC);

} catch (PDOException $e) {
    echo "error en la conexion";
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Inicio - Kata_MMA</title>
    <link rel="stylesheet" href="estilos/index.css">
    <link rel="icon" type="image/x-icon" href="imagenes/logo.png">
</head>
<body>

    <header>
        <div>
            <img src="imagenes/logo.png" alt="icono_kataMMA" style="width: 50px; height: 50px;">
            <h1>Kata_MMA</h1>
        </div>
        <nav>
            <a class="paginaImportante" href="#Noticias">Noticias</a>

            <?php if ($suscrito): ?>
                <a class="paginaImportante" href="directos.html">Directos</a>
            <?php else: ?>
                <a class="paginaImportante" href="suscripcion.php">Directos</a>
            <?php endif; ?>

            <a class="paginaImportante" href="estadisticas.html">Estadísticas</a>
            <a class="paginaImportante" href="categorias.php">Productos</a>

            <?php if ($sesion_activa): ?>
                <span class="paginaSecundaria saludo">Hola, <strong><?php echo htmlspecialchars($nombre_usuario); ?></strong></span>
                <?php if ($suscrito): ?>
                    <span class="paginaSecundaria" style="color: gold;">⭐ Suscrito</span>
                <?php endif; ?>
                <a class="paginaSecundaria" href="perfil.html">Perfil</a>
                <a class="paginaSecundaria" href="logout.php">Cerrar sesión</a>
            <?php else: ?>
                <a class="paginaSecundaria" href="registro.php">Registrarse o iniciar sesión</a>
            <?php endif; ?>
        </nav>
    </header>

    <hr>

    <div id="portada">
        <img src="imagenes/banner_general.png" alt="imagen de la pelea entre topuria y oliveria">
        <div id="descripcion">
            <h1>SITIO WEB DE NOTICIAS DE LA UFC</h1>
            <p>Aquí encontrarás información sobre los peleadores de la UFC, acceso a directos de eventos y compras de productos.</p>
            <ul>
                <li><img src="imagenes/telefono.png" alt="teléfono" style="width: 24px; height: 24px;"> +34 688 888 888</li>
                <li><img src="imagenes/insta.png" alt="icono de instagram" style="width: 24px; height: 24px;"> kata_mma</li>
                <li><img src="imagenes/yt.png" alt="icono de youtube" style="width: 24px; height: 24px;"> kata_mma</li>
                <li><img src="imagenes/x.png" alt="icono de x" style="width: 24px; height: 24px;"> Kata_MMA</li>
            </ul>
        </div>
    </div>

    <hr>

    <main id="Noticias">
        <h1>NOTICIAS DESTACADAS</h1>

        <?php if (empty($noticias)): ?>
            <p style="padding: 20px; color: #888;">No hay noticias disponibles por el momento.</p>

        <?php else: ?>
            <?php foreach ($noticias as $noticia):
                $parrafos = explode("\n\n", trim($noticia['contenido']));
            ?>
            <article>
                <h2><?php echo htmlspecialchars($noticia['titulo']); ?></h2>

                <p>
                    <?php if (!empty($noticia['imagen_url'])): ?>
                        <img src="<?php echo htmlspecialchars($noticia['imagen_url']); ?>"
                             alt="<?php echo htmlspecialchars($noticia['imagen_alt'] ?? ''); ?>">
                    <?php endif; ?>
                    <?php echo htmlspecialchars($parrafos[0]); ?>
                </p>

                <?php if (!empty($noticia['subtitulo'])): ?>
                    <h3><?php echo htmlspecialchars($noticia['subtitulo']); ?></h3>
                <?php endif; ?>

                <?php for ($i = 1; $i < count($parrafos); $i++): ?>
                    <p><?php echo htmlspecialchars($parrafos[$i]); ?></p>
                <?php endfor; ?>

                <p style="font-size: 12px; color: #aaa; margin-top: 8px;">
                    <?php echo date('d/m/Y', strtotime($noticia['creado_en'])); ?>
                </p>
            </article>
            <?php endforeach; ?>
        <?php endif; ?>

        <?php if (!$sesion_activa): ?>
            <div id="sugerencia">
                <p>Regístrate o inicia sesión para seguir viendo todas las noticias</p><br>
                <a href="registro.php">INICIAR SESIÓN O REGISTRARSE</a>
            </div>
        <?php endif; ?>
    </main>

    <footer>
        <div class="RRSS">
            <a href="https://www.instagram.com"><img src="imagenes/insta.png" alt="insta" style="width: 24px; height: 24px;"></a>
            <a href="https://www.youtube.com"><img src="imagenes/yt.png" alt="youtube" style="width: 24px; height: 24px;"></a>
            <a href="https://www.x.com"><img src="imagenes/x.png" alt="x" style="width: 24px; height: 24px;"></a>
        </div>
        <p>Derechos de autor © 2025 Kata_MMA</p>
        <p><a href="https://www.google.com">Avisos legales</a></p>
    </footer>

</body>
</html>