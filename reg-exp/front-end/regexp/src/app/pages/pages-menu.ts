import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
 /* {
    title: 'E-commerce',
    icon: 'shopping-cart-outline',
    link: '/pages/dashboard',
    home: true,
  },*/
  {
    title: 'About',
    icon: 'home-outline',
    url: '/about',
    target: '_blank',
  },
  {
    title: 'MAIN',
    group: true,
  },
  {
    title: 'Course',
    icon: 'edit-2-outline',

  },
  {
    title: 'Propose a Task',
    icon: 'text-outline',

  },

  {
    title: 'Useful Links',
    icon: 'shuffle-2-outline',
    children: [
      {
        title: '404',
        link: '/pages/miscellaneous/404',
      },
    ],
  },

  {
    title: 'Auth',
    icon: 'lock-outline',
    children: [
      {
        title: 'Login',
        url: '/login',
        target: '_blank'
      },
      {
        title: 'Register',
        url: '/sign-up',
        target: '_blank'
      },
      {
        title: 'Request Password',
        link: '/auth/request-password',
      },
      {
        title: 'Reset Password',
        link: '/auth/reset-password',
      },
    ],
  },
];
