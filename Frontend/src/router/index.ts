import { createRouter } from 'vue-router';
import { routes } from '@/router/Routes';
import { globalNavigationGuard } from '@/router/GlobalNavigationGuard';

const router = createRouter(routes);

router.beforeEach(globalNavigationGuard);

export default router;
