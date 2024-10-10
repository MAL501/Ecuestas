1. ¿Qué sucede si intentas borrar una encuesta que no existe? ¿Cómo lo has controlado?
    No pasa nada al intentar borrar una encuesta que no exista, simplemente al final redirijo a la página de filtros.
    
2. Si introduces en un texto del tipo <style>body background-color:red</style> en uno de los  campos
¿Qué sucede al ver la encuesta? ¿el navegador ejecuta ese código o no? ¿porqué?
¿cómo podrías hacer que se ejecute ese código o que se deje de ejecutar?
    Al insertar esa línea dentro del body el navegador no lo ejecuta porque no esta dónde debe esa etiqueta.
    Se podría hacer usando th junto al atributo uText (th:utext), aunque no es recomendable.
4. ¿Qué has tenido que modificar en el repositorio para filtrar por motivo de búsqueda? ¿has
tenido que escribir el código específico o como lo has realizado?
    He creado un formulario en el cuál al enviarlo crea un ArrayList donde se insertan las encuestas que coincidan con el nivel de satisfacción seleccionado.
    Esta lista se pasaría a la vista y esta la mostraría al usuario.