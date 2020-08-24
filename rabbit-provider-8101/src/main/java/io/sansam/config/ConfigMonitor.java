package io.sansam.config;

/**
 * <p>
 * ConfigMonitor
 * </p>
 *
 * @author houcb
 * @since 2020-08-24 17:30
 */
public class ConfigMonitor {


//    @Autowired
//    ContextRefresher contextRefresher;
//
//    public ConfigMonitor() {
//        super();
//        monitorStart();
//    }
//
//    /**
//     * 开始监听
//     *
//     * @see [类、类#方法、类#成员]
//     */
//    public void monitorStart() {
//        try {
//            File directory = new File("src/main/resources/");
//            FileAlterationObserver observer = new FileAlterationObserver(directory, FileFilterUtils.suffixFileFilter(".properties"));
//            observer.addListener(new FileAlterationListenerAdaptor() {
//                @Override
//                public void onFileChange(File file) {
//                    LOGGER.info("{} changed.", file.getName());
//                    contextRefresher.refresh();
//                }
//
//                @Override
//                public void onFileCreate(File file) {
//                    LOGGER.info("{} created.", file.getName());
//                    contextRefresher.refresh();
//                }
//            });
//            long interval = TimeUnit.SECONDS.toMillis(5); // 周期5秒
//            FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
//            monitor.start();
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//    }

}
