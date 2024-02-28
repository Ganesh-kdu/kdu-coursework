describe('Main Container Component', () => {
    beforeEach(() => {
        cy.visit('http://localhost:5174/');
        cy.get('input#newItem').type('New Item');
        cy.contains('button', 'Submit').click();
    });

    it('Should add an item to the list correctly', () => {
        cy.contains('#list', 'New Item').should('exist');
    });

    it('Should remove checked items correctly', () => {
        cy.get('input#newItem').type('Other Item');
        cy.contains('button', 'Submit').click();
        cy.get('.item input[type="checkbox"]').first().check();        
        cy.contains('button', 'Clear Checked').click();
        cy.contains('#list', 'Other Item').should('exist');
        cy.contains('#list', 'New Item').should('not.exist');
    });

    it('Should contain correct items on search', () => {
        cy.get('input#newItem').type('3rd Item');
        cy.contains('button', 'Submit').click();    
        cy.get("input#search").type("New");
        cy.contains('#list', 'New Item').should('exist');
        cy.contains('#list', '3rd Item').should('not.exist');

        cy.get("input#search").clear();

        cy.get("input#search").type("3");
        cy.contains('#list', 'New Item').should('not.exist');
        cy.contains('#list', '3rd Item').should('exist');

        cy.get("input#search").type("rd");
        cy.contains('#list', 'New Item').should('not.exist');
        cy.contains('#list', '3rd Item').should('exist');

        cy.get("input#search").clear();
        cy.contains('#list', 'New Item').should('exist');
        cy.contains('#list', '3rd Item').should('exist');

    });
});
