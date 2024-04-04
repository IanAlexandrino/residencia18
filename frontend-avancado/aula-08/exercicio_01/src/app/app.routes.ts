import { Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { Componente1Component } from './menu/componente-1/componente-1.component';
import { Componente2Component } from './menu/componente-2/componente-2.component';
import { Componente3Component } from './menu/componente-3/componente-3.component';


export const routes: Routes = [
    {
        path: 'menu-componente',
        component: MenuComponent
    },
    {
        path: 'componente-1',
        component: Componente1Component
    },
    {
        path: 'componente-2',
        component: Componente2Component
    },
    {
        path: 'componente-3',
        component: Componente3Component
    },
];
