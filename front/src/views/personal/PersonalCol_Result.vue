<template>
    <div class="container">
        <div class="w3-container w3-light-grey">
            <h2 class="personal-title">퍼스널컬러 진단 결과</h2>
        </div>
        <div style="display:flex;">
            <div class="personal-image">
                <img :src="befimg" v-if="befimg" alt="기본 이미지">
            </div>
            <div style="margin-top:0px;">
                <div class="res-btncon row" >
                    <div @click="pageChange(1)" class="res-button-start col-1" style="margin-left: 50px;">Q1</div>
                    <div @click="pageChange(2)" class="res-button-end col-1">Q2</div>
                </div>                        
                <div :style="displayPage(1)" class="personal-con row">
                    <div class="personal-highcharts"  style="margin-top:0px">
                        <div id="highchart"></div>            
                    </div>                    
                </div>
                <div :style="displayPage(2)" class="personal-con row">
                    <div class="personal-highcharts" style="height:450px;">
                        <div style=" margin: 103.75px 0 103.75px 0;">
                            <p><span style="color: lightblue;">하늘색</span> 넥타이가 잘어울릴듯</p><br>
                            <p><span style="color: pink;">핑크색</span> 와이셔츠가 잘어울릴듯</p><br>
                            <p><span style="color: navy;">남색</span> 블레이져가 잘어울릴듯</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="personal-btn-grp">
          <button class="btn btn-next personal-btn" @click="movepage" style="width: 240px;">가상 MakeUp</button>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            befimg: localStorage.getItem('befimg'),
            activePage: 1
        }
    },
    methods: {
        pageChange(pageNum) {
            this.activePage = pageNum;
            if (pageNum === 1) {
                this.$nextTick(() => {
                    this.highchart();
                });
            }
        },
        displayPage(pageNum) {
            return this.activePage === pageNum ? { display: 'block' } : { display: 'none' };
        },
        highchart() {
            Highcharts.chart('highchart', {
                chart: {
                    type: 'pie',
                    backgroundColor: 'rgba(218, 218, 218, 0)',
                    custom: {},
                    events: {
                        render() {
                            const chart = this,
                                series = chart.series[0];
                            let customLabel = chart.options.chart.custom.label;

                            if (!customLabel) {
                                customLabel = chart.options.chart.custom.label =
                                    chart.renderer.label(
                                        '웜톤 Total<br/>' +
                                        '<strong>61%</strong><br/>'
                                    )
                                    .css({
                                        color: '#000',
                                        textAnchor: 'middle'
                                    })
                                    .add();
                            }

                            const x = series.center[0] + chart.plotLeft,
                                y = series.center[1] + chart.plotTop -
                                (customLabel.attr('height') / 2);

                            customLabel.attr({
                                x,
                                y
                            });
                            customLabel.css({
                                fontSize: `${series.center[2] / 12}px`
                            });
                        }
                    }
                },
                accessibility: {
                    point: {
                        valueSuffix: '%'
                    }
                },
                title: {
                    text: '가을 웜톤'
                },
                subtitle: {
                    text: '퍼스널컬러 비율'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.0f}%</b>'
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    series: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        borderRadius: 8,
                        dataLabels: [{
                            enabled: true,
                            distance: 20,
                            format: '{point.name}'
                        }, {
                            enabled: true,
                            distance: -15,
                            format: '{point.percentage:.0f}%',
                            style: {
                                fontSize: '0.9em'
                            }
                        }],
                        showInLegend: true
                    }
                },
                series: [{
                    name: 'Registrations',
                    colorByPoint: true,
                    innerSize: '75%',
                    data: [{
                        name: '봄 웜톤',
                        y: 23.9
                    }, {
                        name: '여름 쿨톤',
                        y: 12.6
                    }, {
                        name: '가을 웜톤',
                        y: 37.0
                    }, {
                        name: '겨울 쿨톤',
                        y: 26.4
                    }]
                }]
            });
        },
        movepage() {
            this.$router.push('/personal_MakeUp')
        }
    },
    mounted() {
        if (this.activePage === 1) {
            this.highchart();
        }
    }
}
</script>