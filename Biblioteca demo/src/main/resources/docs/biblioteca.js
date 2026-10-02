/* Las URL relativas conservan el context-path si la aplicación tiene uno. */
async function loadDocumentation() {
    const status = document.getElementById('load-status');
    try {
        const response = await fetch('../v3/api-docs');
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const spec = await response.json();
        if (!window.SwaggerUIBundle) throw new Error('No se pudo cargar Swagger UI');
        // React monta el filtro de forma asincrona; observarlo evita depender de un temporizador.
        const filterObserver = new MutationObserver(() => {
            const filter = document.querySelector('.swagger-ui .operation-filter-input');
            if (filter) {
                filter.placeholder = 'Filtrar por grupo: Autores o Libros';
                filter.setAttribute('aria-label', 'Filtrar operaciones por grupo');
                filterObserver.disconnect();
            }
        });
        filterObserver.observe(document.getElementById('swagger-ui'), { childList: true, subtree: true });
        window.ui = SwaggerUIBundle({
            spec,
            dom_id: '#swagger-ui',
            deepLinking: true,
            filter: true,
            tagsSorter: 'alpha',
            operationsSorter: 'method',
            docExpansion: 'list',
            defaultModelsExpandDepth: 0,
            displayRequestDuration: true,
            displayOperationId: false,
            validatorUrl: null,
            presets: [SwaggerUIBundle.presets.apis],
            layout: 'BaseLayout',
            onComplete() {
                status.hidden = true;
            }
        });
    } catch (error) {
        status.classList.add('load-error');
        status.textContent = `No se pudo cargar la documentación (${error.message}). Comprueba que la aplicación esté disponible y recarga la página.`;
    }
}
loadDocumentation();