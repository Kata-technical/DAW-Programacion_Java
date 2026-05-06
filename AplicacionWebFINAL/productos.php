<?php
session_start();

$cadena_conexion = 'mysql:dbname=aplicacion_web;host=127.0.0.1';
$usuario_bd = 'root';
$clave_bd = '';

$sesion_activa = isset($_SESSION["usuario"]);
$nombre_usuario = $sesion_activa ? $_SESSION["usuario"] : '';
$suscrito = false;

// Recoger id_categoria de la URL
$id_categoria = isset($_GET["categoria"]) ? (int)$_GET["categoria"] : 0;

// Añadir al carrito
$mensaje = '';
if ($sesion_activa && $_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["id_producto"])) {
    $id_prod = (int)$_POST["id_producto"];
    if (!isset($_SESSION["carrito"])) {
        $_SESSION["carrito"] = [];
    }
    if (isset($_SESSION["carrito"][$id_prod])) {
        $_SESSION["carrito"][$id_prod]++;
    } else {
        $_SESSION["carrito"][$id_prod] = 1;
    }
    $mensaje = "Producto añadido al carrito ✓";
}

$categoria = null;
$productos = [];
try {
    $bd = new PDO($cadena_conexion, $usuario_bd, $clave_bd);

    if ($sesion_activa) {
        $stmt = $bd->prepare("SELECT subscrito FROM usuarios WHERE id_usuario = ?");
        $stmt->execute([$_SESSION["id"]]);
        $fila = $stmt->fetch(PDO::FETCH_ASSOC);
        $suscrito = $fila && $fila['subscrito'];
    }

    if ($id_categoria > 0) {
        $stmt = $bd->prepare("SELECT * FROM categorias WHERE id_categoria = ?");
        $stmt->execute([$id_categoria]);
        $categoria = $stmt->fetch(PDO::FETCH_ASSOC);

        $stmt2 = $bd->prepare("SELECT * FROM productos WHERE id_categoria = ? AND stock > 0 ORDER BY nombre ASC");
        $stmt2->execute([$id_categoria]);
        $productos = $stmt2->fetchAll(PDO::FETCH_ASSOC);
    }

} catch (PDOException $e) {}

$num_carrito = 0;
if (isset($_SESSION["carrito"])) {
    foreach ($_SESSION["carrito"] as $cantidad) {
        $num_carrito += $cantidad;
    }
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <?php echo $categoria ? htmlspecialchars($categoria['nombre']) : 'Productos'; ?> - Kata_MMA
    </title>
    <link rel="stylesheet" href="estilos/productos.css">
    <link rel="icon" type="image/x-icon" href="imagenes/logo.png">
</head>

<body>
    <header>
        <div>
            <img src="imagenes/logo.png" alt="icono_kataMMA" style="width: 50px; height: 50px;">
            <h1>Kata_MMA</h1>
        </div>
        <nav>
            <a class="paginaImportante" href="index.php">Noticias</a>
            <?php if ($suscrito): ?>
            <a class="paginaImportante" href="directos.html">Directos</a>
            <?php else: ?>
            <a class="paginaImportante" href="suscripcion.php">Directos</a>
            <?php endif; ?>
            <a class="paginaImportante" href="estadisticas.html">Estadísticas</a>
            <a class="paginaImportante" href="categorias.php">Productos</a>

            <?php if ($sesion_activa): ?>
            <span class="paginaSecundaria saludo">Hola, <strong>
                    <?php echo htmlspecialchars($nombre_usuario); ?>
                </strong></span>
            <a class="paginaSecundaria" href="perfil.html">Perfil</a>
            <a class="paginaSecundaria" href="carrito.php">
                🛒 Carrito
                <?php if ($num_carrito > 0): ?><span class="badge">
                    <?php echo $num_carrito; ?>
                </span>
                <?php endif; ?>
            </a>
            <a class="paginaSecundaria" href="logout.php">Cerrar sesión</a>
            <?php else: ?>
            <a class="paginaSecundaria" href="registro.php">Registrarse o iniciar sesión</a>
            <?php endif; ?>
        </nav>
    </header>
    <hr>

    <div id="portada">
        <h1>
            <?php echo $categoria ? htmlspecialchars($categoria['nombre']) : 'Productos'; ?>
        </h1>
        <?php if ($categoria && !empty($categoria['descripcion'])): ?>
        <p>
            <?php echo htmlspecialchars($categoria['descripcion']); ?>
        </p>
        <?php endif; ?>
        <a href="categorias.php">← Volver a categorías</a>
    </div>
    <hr>

    <main>
        <?php if (!empty($mensaje)): ?>
        <p style="color: green; padding: 10px; text-align:center;">
            <?php echo htmlspecialchars($mensaje); ?>
        </p>
        <?php endif; ?>

        <?php if (empty($productos)): ?>
        <p style="padding: 20px; color: #888;">No hay productos disponibles en esta categoría.</p>
        <?php else: ?>
        <div class="contenedores">
            <?php foreach ($productos as $prod): ?>
            <div class="productos">
                <?php if (!empty($prod['imagen_url'])): ?>
                <img src="<?php echo htmlspecialchars($prod['imagen_url']); ?>"
                    alt="<?php echo htmlspecialchars($prod['nombre']); ?>">
                <?php endif; ?>

                <p><strong>
                        <?php echo htmlspecialchars($prod['nombre']); ?>
                    </strong></p>

                <?php if (!empty($prod['descripcion'])): ?>
                <p>
                    <?php echo htmlspecialchars($prod['descripcion']); ?>
                </p>
                <?php endif; ?>

                <p><strong>
                        <?php echo number_format($prod['precio'], 2, ',', '.'); ?> €
                    </strong></p>
                <p style="font-size:12px; color:#aaa;">Stock:
                    <?php echo $prod['stock']; ?>
                </p>

                <?php if ($sesion_activa): ?>
                <form method="post">
                    <input type="hidden" name="id_producto" value="<?php echo $prod['id_producto']; ?>">
                    <input type="submit" value="Añadir al carrito 🛒">
                </form>
                <?php else: ?>
                <a href="login.php">Inicia sesión para comprar</a>
                <?php endif; ?>
            </div>
            <?php endforeach; ?>
        </div>
        <?php endif; ?>
    </main>

    <footer>
        <div class="RRSS">
            <a href="https://www.instagram.com"><img src="imagenes/insta.png" alt="insta"
                    style="width: 24px; height: 24px;"></a>
            <a href="https://www.youtube.com"><img src="imagenes/yt.png" alt="youtube"
                    style="width: 24px; height: 24px;"></a>
            <a href="https://www.x.com"><img src="imagenes/x.png" alt="x" style="width: 24px; height: 24px;"></a>
        </div>
        <p>Derechos de autor © 2025 Kata_MMA</p>
        <p><a href="https://www.google.com">Avisos legales</a></p>
    </footer>
</body>

</html>