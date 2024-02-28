describe('Main Container Component', () => {
    beforeEach(() => {
        cy.visit('http://localhost:5173/');
        cy.get('input#newItem').type('New Item');
        cy.contains('button', 'Submit').click();
    });

    it('Should add an item to the list correctly', () => {
        cy.contains('#list', 'New Item').should('exist');
    });

    it('Should remove checked items correctly', () => {
        cy.get('.item input[type="checkbox"]').first().check();        
        cy.contains('button', 'Clear Checked').click();
        cy.contains('#list', 'New Item').should('not.exist');
    });
});
