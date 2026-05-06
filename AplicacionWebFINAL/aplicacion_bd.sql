CREATE DATABASE aplicacion_web;
USE aplicacion_web;

-- Usuarios
CREATE TABLE usuarios (
    id_usuario      INT(4) AUTO_INCREMENT,
    nombre          VARCHAR(100) NOT NULL,
    email           VARCHAR(150) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    subscrito boolean DEFAULT false,

    CONSTRAINT PK_usuarios PRIMARY KEY (id_usuario)
);

-- Categorías de productos
CREATE TABLE categorias (
    id_categoria    INT(4) AUTO_INCREMENT,
    nombre          VARCHAR(100) NOT NULL,
    descripcion     VARCHAR (222),

    CONSTRAINT PK_categorias  PRIMARY KEY (id_categoria)
);

-- Productos
CREATE TABLE productos (
    id_producto     INT(4) AUTO_INCREMENT,
    id_categoria    INT(4),
    nombre          VARCHAR(200) NOT NULL,
    descripcion     VARCHAR(200),
    precio          DECIMAL(10,2) NOT NULL,
    stock           INT(6) DEFAULT 0,
    imagen_url      VARCHAR(500),

    CONSTRAINT PK_productos PRIMARY KEY (id_producto),
    CONSTRAINT FK_categoria FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

-- Pedidos
CREATE TABLE pedidos (
    id_pedido           INT(4) AUTO_INCREMENT,
    id_usuario          INT(4) NOT NULL,
    id_producto         INT(4) NOT NULL,
    cantidad            INT(4) NOT NULL DEFAULT 1,
    total               DECIMAL(10,2) NOT NULL,
    fecha_compra        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT PK_pedidos    PRIMARY KEY (id_pedido),
    CONSTRAINT FK_usuario    FOREIGN KEY (id_usuario)  REFERENCES usuarios(id_usuario),
    CONSTRAINT FK_producto   FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE noticias (
    id_noticia  INT(4) AUTO_INCREMENT,
    titulo      VARCHAR(300) NOT NULL,
    subtitulo   VARCHAR(300),
    contenido   TEXT NOT NULL,
    imagen_url  VARCHAR(500),
    imagen_alt  VARCHAR(200),
    creado_en   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT PK_noticias PRIMARY KEY (id_noticia)
);

INSERT INTO noticias (titulo, subtitulo, contenido, imagen_url, imagen_alt) VALUES
(
  'Donald Trump, la última esperanza de Jon Jones para pelear en UFC White House',
  'La falta de confianza del CEO de la UFC',
  'Jon Jones sorprendió al acercarse a Dana White con un mensaje directo: "Quiero pelear en la Casa Blanca; lo digo en serio". Según contó el presidente de la UFC en Sirius XM, el excampeón incluso aseguró que volvería a someterse a pruebas antidopaje para encabezar el evento especial del 4 de julio de 2026. Sin embargo, la reacción de White no fue tan entusiasta.\n\nAunque Jones es uno de los peleadores más reconocidos en la historia de la promotora, Dana White dejó en claro que la fiabilidad es clave en un evento de tal magnitud. El mandamás de la UFC señaló que necesita atletas en los que pueda confiar plenamente, y citó a Conor McGregor como ejemplo de compromiso, dejando entrever que "Bones" no entra en esa categoría.\n\nEl analista Ariel Helwani no descarta que Donald Trump pueda intervenir para que Jon Jones encabece el evento en la Casa Blanca.',
  'imagenes/noticia1.png',
  'Donald Trump y Jon Jones dándose la mano al lado del octógono'
),
(
  'Conor McGregor se baja de la contienda presidencial en Irlanda: "Esto es solo el comienzo"',
  NULL,
  'Conor McGregor sorprendió al anunciar su salida de la carrera presidencial en Irlanda, apenas semanas después de confirmar su intención de postularse. El excampeón de la UFC publicó un comunicado en sus redes sociales explicando que, tras conversar con su familia y reflexionar profundamente, decidió que "no es lo correcto que debe hacer en este momento".\n\nEl peleador de 37 años había recorrido distintas regiones del país y asistido a eventos internacionales en su primer intento por incursionar en la política. Aún así, dejó claro que esto no es un adiós definitivo, sino una pausa estratégica.',
  'imagenes/noticia2.png',
  'McGregor con traje hablando'
),
(
  'La confesión de Makhachev que ha sorprendido a todos: "Estoy usando creatina por primera vez"',
  NULL,
  'Islam Makhachev ha transformado su físico con la misión de convertirse en doble campeón de la UFC. El peleador daguestaní dejó vacante el cinturón de peso ligero el pasado mayo, anunciando que subiría a peso wélter para enfrentarse a Jack Della Maddalena por el cinturón. Ese combate será el evento estelar del UFC 322 que se disputará el próximo 16 de noviembre en el Madison Square Garden, Nueva York.\n\nNo hay que olvidar que Makhachev era un peso ligero grande, que cortaba mucho peso para poder cumplir con la báscula. Con esta subida de peso, el peleador daguestaní ha reconocido que por fin está feliz y puede entrenar a mayor rendimiento, así como dar uso de suplementos que le ayuden con este cambio.',
  'imagenes/makachev_noticia3.png',
  'Makhachev en el octógono celebrando una victoria'
  
);
INSERT INTO noticias (titulo, subtitulo, contenido, imagen_url, imagen_alt) VALUES
(
  'Ilia Topuria apunta al título: “Estoy listo para dominar la división”',
  'El hispano-georgiano busca consolidarse como estrella de la UFC',
  'Ilia Topuria ha declarado que se siente en el mejor momento de su carrera y preparado para enfrentarse a cualquier rival en la división de peso pluma. Tras su última victoria por KO, el luchador ha escalado posiciones rápidamente en el ranking.\n\nExpertos y analistas coinciden en que podría ser el próximo contendiente al cinturón si mantiene este nivel.',
  'imagenes/topuria.png',
  'Topuria celebrando victoria'
),
(
  'UFC anuncia evento histórico en España para 2026',
  'Madrid podría albergar su primer gran evento numerado',
  'La UFC está considerando seriamente organizar un evento en España, con Madrid como principal candidata. Esto marcaría un hito en la expansión europea de la compañía.\n\nSe espera la participación de luchadores europeos destacados, incluyendo talentos emergentes del panorama español.',
  'imagenes/ufc_madrid.png',
  'Octógono con bandera de España'
),
(
  'Alex Pereira defiende su título con un KO espectacular',
  NULL,
  'El campeón de peso semipesado Alex Pereira volvió a demostrar su poder con un impresionante nocaut en el segundo asalto. Su striking sigue siendo uno de los más temidos en toda la UFC.\n\nCon esta victoria, refuerza su legado como uno de los mejores kickboxers en la historia del MMA.',
  'imagenes/pereira.png',
  'Pereira noqueando a su rival'
);




INSERT INTO categorias (nombre, descripcion) VALUES
('Equipamiento MMA', 'Guantes, espinilleras y material de entrenamiento para artes marciales mixtas'),
('Ropa UFC', 'Camisetas, pantalones y merchandising oficial de UFC'),
('Suplementos deportivos', 'Productos para mejorar el rendimiento físico en combate'),
('Entrenamiento y fitness', 'Accesorios para preparación física de luchadores'),
('Coleccionables UFC', 'Artículos exclusivos y memorabilia de peleadores');




INSERT INTO productos (id_categoria, nombre, descripcion, precio, stock, imagen_url) VALUES
(1, 'Guantes MMA Pro Elite', 'Guantes profesionales para entrenamiento y combate', 79.99, 50, 'imagenes/guantes_mma.png'),
(1, 'Espinilleras Muay Thai', 'Protección resistente para entrenamientos intensivos', 64.50, 30, 'imagenes/espinilleras.png'),
(2, 'Camiseta oficial UFC Fight Night', 'Camiseta transpirable usada en eventos oficiales', 34.99, 100, 'imagenes/camiseta_ufc.png'),
(2, 'Shorts MMA Venum', 'Pantalones cortos ligeros para combate', 49.99, 70, 'imagenes/shorts_mma.png'),
(3, 'Proteína Whey Combat', 'Suplemento proteico para recuperación muscular', 59.99, 40, 'imagenes/proteina.png'),
(3, 'Creatina Monohidratada', 'Mejora la fuerza y el rendimiento explosivo', 29.99, 60, 'imagenes/creatina.png'),
(4, 'Cuerda de salto profesional', 'Ideal para mejorar cardio y coordinación', 19.99, 120, 'imagenes/comba.png'),
(4, 'Saco de boxeo 40kg', 'Saco resistente para entrenamiento de golpeo', 129.99, 20, 'imagenes/saco_boxeo.png'),
(5, 'Figura coleccionable Conor McGregor', 'Figura detallada del excampeón de UFC', 89.99, 15, 'imagenes/mcgregor_figura.png'),
(5, 'Póster firmado Jon Jones', 'Edición limitada firmada por el campeón', 149.99, 10, 'imagenes/jones_poster.png');